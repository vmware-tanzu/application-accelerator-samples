#!/bin/bash

default_workload_namespace=workloads
default_service_namespace=service-instances
default_rabbit_name=rmq-hungryman
default_mysql_name=mysql-hungryman
default_use_knative_eventing=no
accept=no

replace="sed -i"  


if [[ $OSTYPE == 'darwin'* ]]; then
  replace="sed -i ''"  
fi

# Get user input

while [ "$accept" != "yes" ]
do
	printf '\nWorkload Namespace (default %s): ' "'$default_workload_namespace'"
	
	read workloadNamespace
	
	if [ -z "$workloadNamespace" ]
	then
	   workloadNamespace=$default_workload_namespace
	fi
	
	printf 'Service Instance Namespace (default %s): ' "'$default_service_namespace'"
	
	read serviceNamespace
	
	if [ -z "$serviceNamespace" ]
	then
	   serviceNamespace=$default_service_namespace
	fi
	
	printf 'RabbitMQ Cluster Name (default %s): ' "'$default_rabbit_name'"
	
	read rabbitMQName
	
	if [ -z "$rabbitMQName" ]
	then
	   rabbitMQName=$default_rabbit_name
	fi
	
	printf 'MySQL Instance Name (default %s): ' "'$default_mysql_name'"
	
	read mySQLName
	
	if [ -z "$mySQLName" ]
	then
	   mySQLName=$default_mysql_name
	fi
		
	printf 'Use KNative Eventing yes/no (default no): ' 	
		
	read useKNativeEventing
	
	if [ -z "$useKNativeEventing" ]
	then
	   useKNativeEventing=$default_use_knative_eventing
	fi
	
	echo ' '
	echo Configured Options:
	printf '   Workload Namespace: %s\n' "[$workloadNamespace]"
	printf '   Service Instance Namespace: %s\n' "[$serviceNamespace]"
	printf '   RabbitMQ Cluster Name: %s\n' "[$rabbitMQName]"
	printf '   MySQL Instance Name: %s\n' "[$mySQLName]"
	printf '   KNative Eventing: %s\n' "[$useKNativeEventing]"
	
	echo ' '
	printf 'Accept these values: yes/no (default %s)? ' "yes"
	
	read accept
	
	if [ -z "$accept" ]
	then
	   accept=yes
	fi
done

# Apply user inputs to templates

cp ./templates/mysqlTemplate.yaml ./mysql.yaml
$replace "s/mysql_instance_name/$mySQLName/g" ./mysql.yaml
$replace "s/mysql_service_namespace/$serviceNamespace/g" ./mysql.yaml
$replace "s/mysql_workloads_namespace/$workloadNamespace/g" ./mysql.yaml


cp ./templates/rmqTemplate.yaml ./rmq.yaml
$replace "s/rmq_instance_name/$rabbitMQName/g" ./rmq.yaml
$replace "s/rmq_service_namespace/$serviceNamespace/g" ./rmq.yaml
$replace "s/rmq_workloads_namespace/$workloadNamespace/g" ./rmq.yaml


#Determine which app eventing configuration should be u$replace 

if [ "$useKNativeEventing" != "yes" ]
then
	cp ./templates/workloadsRMQTeamplate.yaml ./workloads.yaml
else
    cp ./templates/workloadsKNEventingTemplate.yaml ./workloads.yaml
    cp ./templates/kneventingTemplate.yaml ./kneventing.yaml
    $replace "s/rmq_instance_name/$rabbitMQName/g" ./kneventing.yaml
    $replace "s/workload_namespace/$workloadNamespace/g" ./kneventing.yaml
    $replace "s/rmq_service_namespace/$serviceNamespace/g" ./kneventing.yaml 
fi

$replace "s/rmq_instance_name/$rabbitMQName/g" ./workloads.yaml
$replace "s/mysql_instance_name/$mySQLName/g" ./workloads.yaml
$replace "s/workload_namespace/$workloadNamespace/g" ./workloads.yaml

# Apply resources to the cluster

echo "Applying inputs to the cluster"

kubectl create ns $serviceNamespace
kubectl create ns $workloadNamespace
kubectl apply -f ./mysql.yaml
kubectl apply -f ./rmq.yaml

echo ""
echo "Waiting for service instances to spin up."

kubectl wait --for=condition=ready --timeout=300s pod -l app.kubernetes.io/instance=$mySQLName -n $serviceNamespace 
kubectl wait --for=condition=ready --timeout=300s pod -l app.kubernetes.io/name=$rabbitMQName -n $serviceNamespace

if [ "$useKNativeEventing" == "yes" ]
then
    kubectl apply -f ./kneventing.yaml
fi

kubectl apply -f ./workloads.yaml

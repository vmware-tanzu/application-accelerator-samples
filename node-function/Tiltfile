# You will need to modify this file to enable Tilt live debugging
SOURCE_IMAGE = os.getenv("SOURCE_IMAGE", default='source-image-location') # CHANGEME - replace `source-image-location` with your writable repository
LOCAL_PATH = os.getenv("LOCAL_PATH", default='.')
NAMESPACE = os.getenv("NAMESPACE", default='default')
K8S_TEST_CONTEXT = os.getenv("K8S_TEST_CONTEXT", default='')

allow_k8s_contexts(K8S_TEST_CONTEXT)

k8s_custom_deploy(
    'node-function',
    apply_cmd="tanzu apps workload apply -f config/workload.yaml --live-update" +
        " --local-path " + LOCAL_PATH +
        " --source-image " + SOURCE_IMAGE +
        " --namespace " + NAMESPACE +
        " --yes >/dev/null" +
        " && kubectl get workload node-function --namespace " + NAMESPACE + " -o yaml",
    delete_cmd="tanzu apps workload delete -f config/workload.yaml --namespace " + NAMESPACE + " --yes" ,
    deps=['pom.xml', './target/classes'],
    container_selector='workload',
    live_update=[
        sync('./target/classes', '/workspace')
    ]
)

k8s_resource('node-function', port_forwards=["8080:8080"],
    extra_pod_selectors=[{'serving.knative.dev/service': 'node-function'}])

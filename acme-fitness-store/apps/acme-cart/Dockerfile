FROM bitnami/python:3.7
MAINTAINER Bill Shetti "billshetti@gmail.com"
ENV REDIS_HOST="localhost"
ENV REDIS_PORT="6379"
ENV REDIS_PASSWORD=""

ENV TRACER_HOST="localhost"
ENV TRACER_PORT="6832"
ENV JAEGER_HOST_AGENT="localhost"
ENV JAEGER_HOST_PORT="6832"

# needed for redis-cli ; the server is not used
RUN install_packages redis-server

COPY ./requirements.txt /app/requirements.txt
RUN pip3 install -r requirements.txt

ADD . /app
COPY entrypoint/docker-entrypoint.sh /usr/local/bin/
RUN chmod 777 /usr/local/bin/docker-entrypoint.sh
RUN ln -s /usr/local/bin/docker-entrypoint.sh /app # backwards compat

EXPOSE 5000
ENTRYPOINT ["docker-entrypoint.sh"]
#CMD ["python3", "cart.py"]
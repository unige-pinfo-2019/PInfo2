FROM kong:1.1rc1-centos
LABEL description="Centos 7 + Kong 0.14 + kong-oidc plugin"

RUN yum install -y git unzip && yum clean all

COPY ./docker-entrypoint.sh /docker-entrypoint.sh
RUN chmod 777 /docker-entrypoint.sh

COPY ./nginx-custom-static-sso.template  /usr/local/kong/template/nginx-custom-static-sso.template

COPY ./keycloak_rsa_provider-key-pub.pem /tmp
RUN mkdir /usr/local/config-kong
COPY ./config-kong.sh /usr/local/config-kong
RUN chmod 777 /usr/local/config-kong/config-kong.sh

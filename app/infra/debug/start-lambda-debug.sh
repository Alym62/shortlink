echo "Starting lambda in debug mode..."

# Esse comando serve para fazer um build com o SAM e o start da aplicação, os parâmetros
# passados vão criar um rede entre o docker e localstack, vai deixar o container em estado de invocação
# ao invés de recrialos toda vez que a aplicação recebe uma requisição e vai acontecer um skip da atualização
# da imagem docker.
sam build && sam local start-api \
  --port 3000 \
  --warm-containers EAGER \
  --skip-pull-image \
  --docker-network sam-local-net \
  --debug-port 5005 \
  --debug-args "-agentlib:jdwp=transport=dt_docket,server=y,suspend=y,address=*5005"
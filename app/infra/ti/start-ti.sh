docker-compose up -d -p shortlink-ti

aws --endpoint="http://localhost:4566" dynamodb create-table \
  --region "sa-east-1" \
  --table-name "tb_users" \
  --attribute-definitions \
    "AttributeName=user_id,AttributeType=S" \
  --key-schema \
    "AttributeName=user_id,KeyType=HASH" \
  --provisioned-troughput \
    "ReadCapacityUnits=5,WriteCapacityUnits=5"
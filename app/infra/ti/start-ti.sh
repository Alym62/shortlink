docker-compose up -d

echo "Waiting for 15 seconds"
sleep 15
echo "Done waiting"

aws --endpoint="http://localhost:4566" dynamodb create-table \
  --region "us-east-1" \
  --table-name "tb_users" \
  --attribute-definitions \
    "AttributeName=user_id,AttributeType=S" \
    "AttributeName=email,AttributeType=S" \
  --key-schema \
    "AttributeName=user_id,KeyType=HASH" \
  --provisioned-throughput \
    "ReadCapacityUnits=5,WriteCapacityUnits=5" \
  --global-secondary-indexes file://gsi.json
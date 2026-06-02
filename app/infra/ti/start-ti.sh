docker-compose up -d

echo "Waiting for 15 seconds"
sleep 15
echo "Done waiting"

# Create table users
echo "Create table users"
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
  --global-secondary-indexes file://gsi-tb-users.json

# Create table links
echo "Create table links"
aws --endpoint="http://localhost:4566" dynamodb create-table \
  --region "us-east-1" \
  --table-name "tb_links" \
  --attribute-definitions \
    "AttributeName=link_id,AttributeType=S" \
    "AttributeName=user_id,AttributeType=S" \
  --key-schema \
    "AttributeName=link_id,KeyType=HASH" \
  --provisioned-throughput \
      ReadCapacityUnits=5,WriteCapacityUnits=5 \
  --global-secondary-indexes file://gsi-tb-links.json

# Create queue in SQS
echo "Create queue in SQS"
aws --endpoint="http://localhost:4566" sqs create-queue \
  --region "us-east-1" \
  --queue-name update-count-link.fifo \
  --attribute '{
    "FifoQueue": "true",
    "ContentBasedDeduplication": "true",
    "MessageRetentionPeriod": "1209600"
  }'

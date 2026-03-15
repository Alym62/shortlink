docker-compose up -d

Write-Host "Waiting for 15 seconds"
Start-Sleep -Seconds 15
Write-Host "Done waiting"

aws --endpoint="http://localhost:4566" dynamodb create-table `
  --region "us-east-1" `
  --table-name "tb_users" `
  --attribute-definitions `
    "AttributeName=user_id,AttributeType=S" `
  --key-schema `
    "AttributeName=user_id,KeyType=HASH" `
  --provisioned-throughput `
    "ReadCapacityUnits=5,WriteCapacityUnits=5"
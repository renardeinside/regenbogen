deploy:
	sbt assembly
	sam package --s3-bucket renarde-lambda --output-template-file target/packaged.yaml
	aws cloudformation deploy --template-file target/packaged.yaml --stack-name regenbogen-lambda-prod --capabilities CAPABILITY_IAM

set-webhook:
	curl --request POST  \
	--url https://api.telegram.org/bot${REGENBOGEN_BOT_TOKEN}/setWebhook --header 'content-type: application/json' \
	--data '{"url": "https://d5xdagxq13.execute-api.eu-central-1.amazonaws.com/live"}'

invocation:
	sbt assembly && curl http://127.0.0.1:3000/


deploy:
	sbt assembly

set-webhook:
	curl --request POST  \
	--url https://api.telegram.org/bot${REGENBOGEN_BOT_TOKEN}/setWebhook --header 'content-type: application/json' \
	--data '{"url": "https://d5xdagxq13.execute-api.eu-central-1.amazonaws.com/live"}'

invocation:
	sbt assembly && curl http://127.0.0.1:3000/
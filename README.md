# Regenbogen 
Weather forecasts via Telegram Bot written in Scala.

## How-tos
- Login to Heroku, create your app and link it to heroku git:
```
heroku login
heroku create
heroku git:remote -a <your application name>
``` 
- Setup config variables:
```
heroku config:set REGENBOGEN_BOT_TOKEN=<your token here>
heroku config:set REGENBOGEN_DS_KEY=<your DarkSky key here>
heroku config:set REGENBOGEN_WEBHOOK_URL=$(heroku info -s | grep web_url | cut -d= -f2)
```
- To compile the code and deploy application to Heroku, run:
```
sbt assembly deployHeroku
```
## Powered by 
Weather Data is powered by brilliant service by Dark Sky - https://darksky.net/poweredby/
 
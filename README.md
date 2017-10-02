# aws-alexa-stcolumban-menu
An Amazon Alexa skill to get today's menu selection for St. Columban school in Loveland, Ohio

# Getting Started
This skill requires [Node.js](https://nodejs.org/en/download/) >= v6.10.1, and gulp >= 3.9.1.  Use the following commands to get started after installing Node.js and cloning this repository.
```sh
$ npm i --global gulp
$ cd nodejs
$ npm i
```

# Building and Testing
To build and test the application locally, you will need to setup some environment variables in order for some of the integration tests to run:
* AlexaSkillId = amzn1.ask.skill.[your-skill-id]
* DailyBibleVerseUrl = https://www.biblegateway.com/votd/get/?format=json&version=NIV
* GetMenuOutputUrl = https://s3.amazonaws.com/lambda-function-bucket-us-east-1-1486176755721/SaintColumban/dailyMenuOutputUtterances.json
* MenuType = application/json
* MenuUrl = https://s3.amazonaws.com/lambda-function-bucket-us-east-1-1486176755721/SaintColumban/menu.json

After those are setup, you can run these commands to build and test:
```sh
$ cd nodejs
$ gulp
```

# Deploying
To deploy this to AWS, run `gulp dist` - this will create a zip file called `nodejs/SaintColumbanV2.zip` and upload it to an AWS function called `SaintColumbanV2`.  You will need an AWS credentials file setup, per the [documentation on Amazon](http://docs.aws.amazon.com/cli/latest/userguide/cli-config-files.html).  This uses an npm package called [gulp-awslambda](https://www.npmjs.com/package/gulp-awslambda) to upload the function.  It will do most of the setup needed, but you will need to manually setup the environment variables as described above in the AWS console for the function.

# Testing in AWS
The following request template can be used to test this in AWS:
```json
{
  "session": {
    "new": false,
    "sessionId": "amzn1.echo-api.session.[unique-id]",
    "attributes": {},
    "user": {
      "userId": "amzn1.ask.account.[unique-id]"
    },
    "application": {
      "applicationId": "amzn1.ask.skill.[skill-id]"
    }
  },
  "version": "1.0",
  "request": {
    "locale": "en-US",
    "timestamp": "2016-10-27T21:06:28Z",
    "type": "IntentRequest",
    "requestId": "amzn1.echo-api.request.[unique-id]",
    "intent": {
      "slots": {
          "MenuDate": {
              "value": "next week"
          }
      },
      "name": "GetMenuIntent"
    }
  },
  "context": {
    "AudioPlayer": {
      "playerActivity": "IDLE"
    },
    "System": {
      "device": {
        "supportedInterfaces": {
          "AudioPlayer": {}
        }
      },
      "application": {
        "applicationId": "amzn1.ask.skill.[unique-id]"
      },
      "user": {
        "userId": "amzn1.ask.account.[unique-id]"
      }
    }
  }
}
```

var Alexa = require('alexa-sdk');
var lib = require('./lib');
var appId = process.env.AlexaSkillId;

var helpHandlers = {
    'LaunchRequest': () => {
        this.emit(':tell', 'Sorry, you need to say something like "Alexa, ask Saint Columban what\'s on the lunch menu today" or "Alexa, ask Saint Columban what\'s for lunch next Friday".');
    },
    'AMAZON.HelpIntent': () => {
        this.emit(':tell', 'Sorry, you need to say something like "Alexa, ask Saint Columban what\'s on the lunch menu today" or "Alexa, ask Saint Columban what\'s for lunch next Friday".');
    }
}

exports.handler = function(event, context, callback) {
    var alexa = Alexa.handler(event, context, callback);
    alexa.appId = appId;
    alexa.registerHandlers(lib.Skills.DailyMenu.Handlers, lib.Skills.BibleVerse.Handlers, lib.Skills.DaysLeft.Handlers, helpHandlers);
    alexa.execute();
};
'use strict';

var httpClient = require('../../services/httpClient');
var chrono = require('chrono-node');
var moment = require('moment-timezone');
var _ = require('lodash');
require('format4js');

var handlers = {
    'GetMenuIntent': function() {
        var _this = this;
        skill.main(_this);
    }
};

var skill = {
    menu: {},
    output: {},
    loadFiles: function() {
        var menuPromise = httpClient.get(process.env.MenuUrl).send();
        menuPromise.then((response) => {
            return response;
        }, (error) => {
            console.log(`Error getting menu: ${JSON.stringify(error)}`);
        }).catch((ex) => {
            console.log(`Exception getting menu: ${JSON.stringify(ex)}`);
        });
    
        var outputPromise = httpClient.get(process.env.GetMenuOutputUrl).send();
        outputPromise.then((response) => {
            return response;
        }, (error) => {
            console.log(`Error getting output: ${JSON.stringify(error)}`);
        }).catch((ex) => {
            console.log(`Exception getting output: ${JSON.stringify(ex)}`);
        });
    
        return Promise.all([menuPromise, outputPromise]);
    },
    getDailyMenu: function(menuDateInput) {
        var referenceDate = moment.tz("US/Eastern");
        var menuDate = chrono.parse(menuDateInput, referenceDate)[0].start.date();
        var menuDateString = moment(menuDate).format("dddd, MMMM Do");
        var menuDateKey = moment(menuDate).format('M/D/YYYY');
        
        var menuEntry = this.menu.dates.find(d => d.date === menuDateKey);

        if(!menuEntry) {
            return String.format(this.output.noMenuFound, menuDateString);
        }
        
        var entrees = _.escape(_.map(menuEntry.entree.split(';'), (entree) => { return _.find(this.menu.entrees, ['id', entree]).description; }).join(' or '));
        var veggie = _.escape(_.find(this.menu.veggies, ['id', menuEntry.veggie]).description);
        var treat = _.trim(menuEntry.treat).length > 0 ? _.escape(_.find(this.menu.treat, ['id', menuEntry.treat]).description) : undefined;

        var randomQuote = _.escape(_.escapethis.output.randomQuotes[_.random(0, this.output.randomQuotes.length - 1)]);
        if(treat) {
            return String.format(this.output.outputWithTreat, menuDateString, entrees, veggie, treat, randomQuote);
        } else {
            return String.format(this.output.outputWithNoTreat, menuDateString, entrees, veggie, randomQuote);
        }
    },
    main: function(handler) {
        var menuDate = _.get(handler, 'event.request.intent.slots.MenuDate.value', 'today');
        try {
            this.loadFiles().then((completed) => {
                this.menu = JSON.parse(completed[0].body);
                this.output = JSON.parse(completed[1].body);

                handler.emit(':tell', this.getDailyMenu(menuDate));
            }, (error) => {
                console.log(`Failure: ${JSON.stringify(error)}`);
                handler.emit(':tell', `I'm sorry, there was a problem getting the menu from St. Columban for ${menuDate}.  Try again later.`);
            });
        } catch(e) {
            console.log(`Exception: ${JSON.stringify(e)}`);
            handler.emit(':tell', `I'm sorry, there was a problem getting the menu from St. Columban for ${menuDate}.  Try again later.`);
        }
    }
};

module.exports = handlers;
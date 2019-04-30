'use strict';
const moment = require('moment');

var handlers = {
    'GetDaysLeftIntent': function() {
        var today = moment();
        var current = moment();
        var lastDay = moment('2019-05-30');

        var daysLeft = 0;
        if (lastDay.diff(current, 'days') < 0) {
            daysLeft = -1;
        }
        
        while (lastDay.diff(current, 'days') >= 0) {
            if(current.isoWeekday() < 6) {
                daysLeft++;
            }
            current = current.add(1, 'days');
        }

        if (daysLeft > 3) {
            daysLeft--; // Remove Memorial Day
        } 
        
        if (daysLeft >= 0) {
            var todayFactor = today.hour() > 15 ? 1 : 0;
            daysLeft -= todayFactor;
        }

        var responseText;
        if (daysLeft == 1) {
            responseText = 'Only one more day of school! Make it great!';
        } else if (daysLeft == 7) {
            responseText = 'Lucky number seven - that\'s how many days you have left!';
        } else if (daysLeft == 10) {
            responseText = 'Ten days to go - you can do it!'
        } else if (daysLeft > 0) {
            responseText = `You only have ${daysLeft} days of school left. Just keep learning, just keep learning, just keep learning, learning, learning!`;
        } else {
            responseText = 'School\'s out, hooray! I love Summer! Don\'t you?';
        }
        this.emit(':tell', responseText);
    }
};

module.exports = handlers;
/* jshint expr: true */ // So we can use chai assertions
'use strict';

const expect = require('chai').expect;
const fixture = require('../../../lib/skills/bibleVerse/handlers').GetDailyBibleVerseIntent;
const services = require('../../../lib/services');
const http = require('http');

describe('GetDailyBibleVerseIntent', () => {
    beforeEach(() => {
        process.env.DailyBibleVerseUrl = 'http://www.biblegateway.com/votd/get/?format=json&version=NIV';
    })

    it('should get today\'s bible verse', () => {
        fixture();
        setTimeout(() => {
            console.log('done');
        }, 5000);
    });

    it('should get verse', () => {
        services.HttpClient.get('http://www.biblegateway.com/votd2/get/?format=json&version=NIV').headers({'Accept': 'application/json'}).send((response) => {
            console.log(`GetDailyBibleVerseIntent success: ${response}`);
            var r = JSON.parse(response.body).votd;
            var book = r.reference.substring(0, r.reference.lastIndexOf(' '));
            var chapterAndVerse = r.reference.substring(r.reference.lastIndexOf(' ')).split(':', 2);
            var chapter = chapterAndVerse[0];
            var verse = chapterAndVerse[1];

            console.log(`Today's bible verse is from ${book}, chapter ${chapter}, verse ${verse}.  ${r.content}`);
        }, (error) => {
            console.log(`GetDailyBibleVerseIntent error: ${error}`);
            console.log('Sorry, there was a problem reading today\'s bible verse');
        });        
    });

    it('should make an http request', () => {
        var options = {
            host: 'example.com',
            port: 80,
            path: '/foo.html'
          };
          
          http.get(options, function(resp){
            resp.on('data', function(chunk){
              console.log(`chunk: ${chunk}`);
              //do something with chunk
            });
          }).on("error", function(e){
            console.log("Got error: " + e.message);
          });        
    });
});
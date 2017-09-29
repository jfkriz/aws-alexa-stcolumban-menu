/* jshint expr: true */ // So we can use chai assertions
'use strict';

const expect = require('chai').expect;
const fixture = require('../../../lib/skills/bibleVerse/handlers').GetDailyBibleVerseIntent;

describe('GetDailyBibleVerseIntent', () => {
    beforeEach(() => {
        process.env.DailyBibleVerseUrl = 'https://www.biblegateway.com/votd/get/?format=json&version=NIV';
    })

    it('should get today\'s bible verse', () => {
        fixture();
    });
});
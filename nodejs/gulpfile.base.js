'use strict';

var gulp = require('gulp');
var mocha = require('gulp-mocha');
var jshint = require('gulp-jshint');

module.exports = {
    lint : function() {
        return gulp.src(['lib/**/*.js', 'test/**/*.js'])
               .pipe(jshint())
               .pipe(jshint.reporter('default'));
    },
    test : function() {
        return gulp.src('test/**/*.spec.js', { read : false })
            .pipe(mocha({ reporter : 'nyan' }));
    }
};
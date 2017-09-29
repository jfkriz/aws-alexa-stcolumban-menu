'use strict';

var gulp = require('gulp');
var gulp_base = require('./gulpfile.base');
var aws_lambda = require('gulp-awslambda');
var zip = require('gulp-zip');
var rimraf = require('rimraf');
var install = require('gulp-install');

gulp.task('test', gulp_base.test);
gulp.task('lint', gulp_base.lint);

gulp.task('default', ['test', 'lint']);

gulp.task('-clean-dist', function(cb) {
    rimraf('{dist,SaintColumbanV2.zip}', cb);
});

gulp.task('-copy-dist', ['-clean-dist'], function() {
    return gulp.src(['index.js', 'package.json', 'lib/**/**'], {base: './'}).pipe(gulp.dest('./dist'));
});

gulp.task('-prep-dist', ['-copy-dist'], function() {
    return gulp.src('./dist/package.json').pipe(install({production: true}));
});

gulp.task('dist', ['-prep-dist'], function() {
    return gulp.src('dist/**/**', { nodir: true, dot: true })
        .pipe(zip('SaintColumbanV2.zip', {}))
        .pipe(aws_lambda('SaintColumbanV2', {}))
        .pipe(gulp.dest('.'));
});
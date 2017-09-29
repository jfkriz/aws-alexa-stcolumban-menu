'use strict';

const gulp = require('gulp');
const gulp_base = require('./gulpfile.base');
const aws_lambda = require('gulp-awslambda');
const zip    = require('gulp-zip');
const shell = require('gulp-shell');
const rimraf = require('rimraf');

gulp.task('test', gulp_base.test);
gulp.task('lint', gulp_base.lint);

gulp.task('default', ['test', 'lint']);0

gulp.task('-clean-dist', function(cb) {
    rimraf('{dist,SaintColumbanV2.zip}', cb);
});

gulp.task('-copy-dist', ['-clean-dist'], function() {
    return gulp.src(['index.js', 'package.json', 'lib/**/**'], {base: './'}).pipe(gulp.dest('dist'));
});

gulp.task('-prep-dist', ['-copy-dist'], shell.task('npm i --prod', {cwd: './dist', verbose: true}));

gulp.task('dist', ['-prep-dist'], function() {
    return gulp.src('dist/**/**', { base:'dist' })
        .pipe(zip('SaintColumbanv2.zip', {}))
        .pipe(aws_lambda('SaintColumbanV2', {}))
        .pipe(gulp.dest('.'));
});
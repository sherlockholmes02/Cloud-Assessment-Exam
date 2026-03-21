package com.example.cloudassessmentexam.core.usecase

abstract class UseCase<P, R> {
    abstract fun execute(param: P): R;
}

abstract class UseCaseNoParam<R> {
    abstract fun execute(): R;
}
# Coroutine

## Introduction

`delay()` is a suspend function which can be called from a coroutine or another suspend function.

`suspend` is a special modifier found in kotlin.

**suspending function-** A function with a suspend modifier is known as suspending function. The suspending functions are only allowed to be called from a coroutine or another suspending function. 

**Two ways of creating coroutines-**
1. `GlobalScope.launch()`
2. `runBlocking()`

`GlobalScope.launch{}` ( & `async{}` ) is a coroutine which is non-blocking in nature.
`runBlocking{}` is a coroutine which blocks the current thread.

## Coroutine builders

Coroutine builders are used for creating coroutine.

**Important coroutine builders-**
1. runBlocking - generally used to write test cases to test the suspending functions.
2. launch
2. async

e.g. `GlobalScope.launch{}, GlobalScope.async{}, runBlocking{}`


**GlobalScope.launch{} vs launch{}-**

`GlobalScope.launch{}` is a top level coroutine that can survive the entire lifecycle of the application. **GlobalScope** is a singleton class.
`launch{}` creates coroutine at local scope, get destroyed when current scope get destroyed.
`launch{}` (also `async{}`) coroutine inherits the properties of the immediate parent coroutine i.e. if you run it inside `runBlocking{}` which wraps the main method, it will execute in main thread.

**launch vs async-**

`launch` returns a `Job` object.
`async` returns a `Deferred` object (`Deferred` is an interface which implements `Job` interface)

`launch` starts a new coroutine which does not return the result to the caller - **"fire and forget"** nature.
`async` starts a new coroutine and allows you to return a result with a suspend function called `await`.

## Coroutine cancellation

The coroutine should be **cooperative** to cancel it.

**Cooperative coroutines-**
1. Only those suspending functions that belong to `kotlinx.coroutine` package will make coroutine cooperative. e.g. delay(), `yield(), withContext(), withTimeout()` etc.
2. Use `CoroutineScope.isActive` boolean flag

## Handling exceptions

Suspending function can not be run from a finally block
Use `withContext(NonCancellable){}` to execute suspend function in finally block

## Timeout

`withTimeout`

`withTimeoutOrNull`

These are also coroutine builders just like launch and async builders.

## Composing suspending function

**Sequential execution-**
_code executes withing a coroutine by default sequential._

**Concurrent/parallel execution-**
	_can be achieved using async/launch builder_

**Lazy coroutine execution-**
	_can be achieved using `start = CoroutineStart.LAZY` inside `async` e.g. `async(start = CoroutineStart.LAZY)`. This means if this coroutine's result is not used in the program compiler will not executes a lazy coroutine._

## CoroutineScope, CoroutineContext and Dispatchers


### CoroutineScope-

Every coroutine has its own CoroutineScope instance attached to it. CoroutineScope can be accessed using this keyboard from inside the coroutine.

### CoroutineContext-

Similar to CoroutineScope, every coroutine has its own CoroutineContext. Unlike CoroutineScope CoroutineContext can be inherited from parent to child. This can be accessed using coroutineContext property from inside a coroutine.

**CoroutineContext has two major component-**
		
1. **Dispatcher**- It decides on which thread the coroutine will execute
   * Default
   * Unconfined
   * Main
   * IO
2. **Job**- is used to control a coroutine
3. CoroutineName


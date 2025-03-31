# RQ1 AccessMode Annotations using GPT 4o Few-Shot + CoT

## Summary

The coverage over the user requirements of the different executions are:

| Prompt  Technique | Model  | EX1 | EX2 | EX3 | EX4 | EX5 | Average | Median | STD Desv |
|:-----------------:|:------:|-----|-----|-----|-----|-----|---------|--------|----------|
|  Few -Shot + CoT  | GPT 4o | 100 | 100 | 100 | 100 | 100 | 100     | 100    | 0        | 

## Base truth (Human Annotations)

```java
    @AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
    @AccessMode(resID = "openvidu", concurrency = 10, sharing = true, accessMode = "READWRITE")
    @AccessMode(resID = "configuration", concurrency = 1, sharing = false, accessMode = "READONLY")
    @AccessMode(resID = "executor", concurrency = 1, accessMode = "READWRITE")
    @AccessMode(resID = "webbrowser", concurrency = 1, accessMode = "READWRITE")
    @AccessMode(resID = "webserver", concurrency = 1, accessMode = "READWRITE")
    @DisplayName("oneToOneChatInSessionChrome")
    @ParameterizedTest
    @MethodSource("data")
    @Tag("Multiuser Test")
    void oneToOneChatInSessionChrome(String mail, String password, String role ) throws URISyntaxException, MalformedURLException, NotLoggedException, ElementNotFoundException, InterruptedException { //197 Lines of code
        int numberpriormessages;
```


- *File*: `TO-DO`

```java
# Execution 1

@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READWRITE")
@AccessMode(resID = "forum", concurrency = 5, sharing = false, accessMode = "READWRITE")
@AccessMode(resID = "webbrowser", concurrency = 2, accessMode = "READWRITE")
@AccessMode(resID = "webserver", concurrency = 2, accessMode = "READWRITE")
@AccessMode(resID = "executor", concurrency = 1, accessMode = "READWRITE")

# Execution 2
@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "openvidumock", concurrency = 10, sharing = true, accessMode = "NOACCESS")
@AccessMode(resID = "forum", concurrency = 1, sharing = false, accessMode = "READWRITE")
@AccessMode(resID = "executor", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "webbrowser", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "webserver", concurrency = 1, accessMode = "READWRITE")

# Execution 3

@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READWRITE")
@AccessMode(resID = "chatservice", concurrency = 1, sharing = false, accessMode = "READWRITE")
@AccessMode(resID = "sessionservice", concurrency = 2, sharing = true, accessMode = "READWRITE")
@AccessMode(resID = "webbrowser", concurrency = 1, accessMode = "READWRITE")

# Execution 4
@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "openvidumock", concurrency = 10, sharing = true, accessMode = "NOACCESS")
@AccessMode(resID = "forum", concurrency = 1, sharing = false, accessMode = "READWRITE")
@AccessMode(resID = "executor", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "webbrowser", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "webserver", concurrency = 1, accessMode = "READWRITE")

# Execution 5

@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "openvidumock", concurrency = 10, sharing = true, accessMode = "NOACCESS")
@AccessMode(resID = "webbrowser", concurrency = 2, accessMode = "READWRITE")  // Multiple browsers used
@AccessMode(resID = "webserver", concurrency = 1, accessMode = "READWRITE")  // Interactions with server (e.g., sending messages)
@AccessMode(resID = "chatService", concurrency = 2, accessMode = "READWRITE")  // Chat system for sending/receiving messages
@AccessMode(resID = "courses", concurrency = 1, accessMode = "READWRITE")  // Access to courses and session data
@AccessMode(resID = "sessionService", concurrency = 1, accessMode = "READWRITE")  // Interaction with session-related data
@AccessMode(resID = "studentService", concurrency = 1, sharing = false, accessMode = "READWRITE")  // For student login and chat interactions
@AccessMode(resID = "teacherService", concurrency = 1, sharing = false, accessMode = "READWRITE")  // For teacher login and chat interactions

# Execution 6

@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "webbrowser", concurrency = 2, accessMode = "READWRITE")
@AccessMode(resID = "chatservice", concurrency = 2, accessMode = "READWRITE")
@AccessMode(resID = "webserver", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "executor", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "sessionservice", concurrency = 5, accessMode = "READWRITE")
@AccessMode(resID = "messagequeue", concurrency = 10, accessMode = "READWRITE")
@AccessMode(resID = "studentservice", concurrency = 5, accessMode = "READWRITE")
@AccessMode(resID = "teacherchatservice", concurrency = 5, accessMode = "READWRITE")
@AccessMode(resID = "studentsession", concurrency = 2, accessMode = "READWRITE")
@AccessMode(resID = "teachersession", concurrency = 2, accessMode = "READWRITE")
@AccessMode(resID = "systemmessages", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "chatlog", concurrency = 1, accessMode = "READWRITE")

# Execution 7
@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READWRITE")
@AccessMode(resID = "webbrowser", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "chatservice", concurrency = 2, accessMode = "READWRITE")
@AccessMode(resID = "webserver", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "sessionmanager", concurrency = 1, accessMode = "READWRITE")

# Execution 8

@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY") // To log in both teacher and student
@AccessMode(resID = "chatservice", concurrency = 10, sharing = true, accessMode = "READWRITE") // For sending and receiving messages in chat
@AccessMode(resID = "sessionservice", concurrency = 2, sharing = true, accessMode = "READWRITE") // For accessing and interacting with sessions
@AccessMode(resID = "browser", concurrency = 2, accessMode = "READWRITE") // For controlling browser interaction with the application
@AccessMode(resID = "systemmessages", concurrency = 1, sharing = false, accessMode = "READWRITE") // For sending and checking system messages
@AccessMode(resID = "webserver", concurrency = 1, accessMode = "READWRITE") // To ensure web interactions (e.g., navigation, clicking) can happen
@AccessMode(resID = "webbrowser", concurrency = 2, accessMode = "READWRITE") // For managing browser actions for teacher and student
@AccessMode(resID = "userresource", concurrency = 2, sharing = true, accessMode = "READWRITE") // For accessing user-related data (teacher and student accounts)

# Execution 9
@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "openvidumock", concurrency = 10, sharing = true, accessMode = "NOACCESS")
@AccessMode(resID = "forum", concurrency = 1, sharing = false, accessMode = "READWRITE")
@AccessMode(resID = "executor", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "webbrowser", concurrency = 2, accessMode = "READWRITE")
@AccessMode(resID = "webserver", concurrency = 2, accessMode = "READWRITE")

# Execution 10

@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "chatservice", concurrency = 2, sharing = false, accessMode = "READWRITE")
@AccessMode(resID = "sessionservice", concurrency = 2, sharing = false, accessMode = "READWRITE")
@AccessMode(resID = "systemmessage", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "ui-chatbox", concurrency = 2, sharing = false, accessMode = "READWRITE")
@AccessMode(resID = "ui-sessions", concurrency = 2, sharing = false, accessMode = "READWRITE")
@AccessMode(resID = "executor", concurrency = 1, accessMode = "READWRITE")





```
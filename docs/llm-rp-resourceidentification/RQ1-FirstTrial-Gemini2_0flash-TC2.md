# RQ1 AccessMode Annotations using Gemini 2.0 Few-Shot + CoT

## Summary

The coverage over the user requirements of the different executions are:

| Prompt  Technique |   Model    | EX1 | EX2 | EX3 | EX4 | EX5 | EX6 | EX7 | EX8 | EX9 | EX10 | 
|:-----------------:|:----------:|-----|-----|-----|-----|-----|-----|-----|-----|-----|------|
|  Few -Shot + CoT  | Gemini 2.0 | 100 | 100 | 100 | 100 | 100 | 100 | 100 | 0   | 

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
@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "openvidumock", concurrency = 10, sharing = true, accessMode = "READWRITE")
@AccessMode(resID = "webbrowser", concurrency = 2, accessMode = "READWRITE")
@AccessMode(resID = "webserver", concurrency = 1, accessMode = "READWRITE")

# Execution 2
@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "executor", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "webbrowser", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "webserver", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "openvidumock", concurrency = 10, sharing = true, accessMode = "NOACCESS")
    

# Execution 3
@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "openvidumock", concurrency = 10, sharing = true, accessMode = "NOACCESS")
@AccessMode(resID = "executor", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "webbrowser", concurrency = 2, accessMode = "READWRITE")
@AccessMode(resID = "webserver", concurrency = 1, accessMode = "READWRITE")    

# Execution 4
@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "openvidumock", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "webbrowser", concurrency = 2, accessMode = "READWRITE")
@AccessMode(resID = "webserver", concurrency = 1, accessMode = "READWRITE")

# Execution 5
@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "webbrowser", concurrency = 2, accessMode = "READWRITE")
@AccessMode(resID = "webserver", concurrency = 1, accessMode = "READONLY")
@AccessMode(resID = "openvidumock", concurrency = 10, sharing = true, accessMode = "READONLY")
    

# Execution 6
@AccessMode(resID = "loginservice", concurrency = 2, sharing = true, accessMode = "READWRITE")
@AccessMode(resID = "webbrowser", concurrency = 2, accessMode = "READWRITE", sharing = false)
@AccessMode(resID = "webserver", concurrency = 2, accessMode = "READWRITE", sharing = true)
@AccessMode(resID = "session", concurrency = 2, accessMode = "READWRITE", sharing = true)
@AccessMode(resID = "course", concurrency = 2, accessMode = "READONLY", sharing = true)
@AccessMode(resID = "openvidu", concurrency = 2, accessMode = "READONLY", sharing = true)


# Execution 7
@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY") // Used for login
@AccessMode(resID = "webbrowser", concurrency = 2, accessMode = "READWRITE") // Two browsers are used (teacher and student) and they interact with the UI
@AccessMode(resID = "webserver", concurrency = 2, accessMode = "READWRITE") // Both browsers interact with the webserver to load pages and send data
@AccessMode(resID = "course", concurrency = 1, accessMode = "READONLY") // Reading course details to navigate to the session
@AccessMode(resID = "session", concurrency = 1, accessMode = "READWRITE") // Accessing the session and interacting with the chat feature (sending/receiving messages)
@AccessMode(resID = "openvidu", concurrency = 2, accessMode = "READWRITE") // Likely used for the underlying communication of the chat feature
@AccessMode(resID = "openvidumock", concurrency = 10, sharing = true, accessMode = "NOACCESS") // Explicitly mentioned in other examples, but the base resource is likely OpenVidu

# Execution 8
@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "webbrowser", concurrency = 2, accessMode = "READWRITE") // Assuming 2 browsers are used concurrently
@AccessMode(resID = "webserver", concurrency = 2, accessMode = "READWRITE")  // Assuming both browsers interact with the server
@AccessMode(resID = "course", concurrency = 2, sharing = true, accessMode = "READONLY") // Both users access course information
@AccessMode(resID = "session", concurrency = 2, accessMode = "READWRITE") // Both users interact with the session (send/receive messages)
@AccessMode(resID = "openvidumock", concurrency = 2, sharing = true, accessMode = "READWRITE") // Assuming mock OpenVidu handles chat


# Execution 9
@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY") // Assuming login is read-only for the test
@AccessMode(resID = "webserver", concurrency = 1, accessMode = "READWRITE") // Interacting with the webserver to navigate and send messages
@AccessMode(resID = "course", concurrency = 1, accessMode = "READONLY") // Reading course information for navigation
@AccessMode(resID = "session", concurrency = 1, accessMode = "READWRITE") // Writing and reading chat messages within the session
@AccessMode(resID = "webbrowser", concurrency = 1, accessMode = "READWRITE") // Controlling the student's browser
@AccessMode(resID = "openvidumock", concurrency = 10, sharing = true, accessMode = "NOACCESS") // Assuming no direct interaction with openvidumock
@AccessMode(resID = "forum", concurrency = 10, sharing = true, accessMode = "NOACCESS") // Assuming no direct interaction with forum
@AccessMode(resID = "executor", concurrency = 1, accessMode = "READWRITE") // Assuming potential interaction for test execution environment
# Execution 10

@AccessMode(resID = "loginservice", concurrency = 10, sharing = true, accessMode = "READONLY")
@AccessMode(resID = "webbrowser", concurrency = 2, accessMode = "READWRITE") // Assuming 2 browsers are used concurrently
@AccessMode(resID = "webserver", concurrency = 1, accessMode = "READWRITE")
@AccessMode(resID = "course", concurrency = 1, accessMode = "READONLY") // Navigating and reading course info
@AccessMode(resID = "session", concurrency = 1, accessMode = "READWRITE") // Sending and receiving messages
@AccessMode(resID = "openvidumock", concurrency = 10, sharing = true, accessMode = "NOACCESS") // Assuming not directly accessed but might be underlying
@AccessMode(resID = "openvidu", concurrency = 10, sharing = true, accessMode = "NOACCESS") // Assuming not directly accessed but might be underlying
@AccessMode(resID = "executor", concurrency = 1, accessMode = "READWRITE") // Assuming potential background tasks



```
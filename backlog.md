# Backlog

## Acceptance test list

### Posting
 
> Alice -> I love the weather today
> Bob -> Damn! We lost!
> Bob -> Good game though.

### Reading

> Alice
> I love the weather today (5 minutes ago)

* Only the message, without the name Alice
* A time, expressed a a string

> Bob
> Good game though. (1 minute ago)
> Bob -> Damn! We lost! (2 minutes ago)

### Following

> Charlie -> I'm in New York today! Anyone wants to have a coffee?
> Charlie follows Alice
> Charlie wall

> Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)
> Alice - I love the weather today (5 minutes ago)

* Charlie messages first. First messages are of the user that called wall command

> Charlie follows Bob
> Charlie wall

> Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)
> Bob - Good game though. (1 minutes ago)
> Bob - Damn! We lost! (2 minute ago)
> Alice - I love the weather today (5 minutes ago)

* after Charlie's messages, Bob messages. The last user being followed is the first being shown

## TODO

* how to manage time in Java
* how to convert from time (300 sec) to string (5 minutes)
* how to store other users messages if I follow them (a stack?) 
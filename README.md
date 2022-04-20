# Weather-API-Project

Group Members: Michael, Lydia, and Vinicius 

Professor: Simon Sultana 

Class: CSSE-350 

 

Team Project – Weather Window 

 

General Project Idea: 

For the ambient interface, we are planning to create a small window that provides information about the weather for a certain place location. This information will be updated after each specified time passes (ex. 10 minutes). The weather information will be based on real time events which will be retrieved from a weather API. There will also be a way to maximize the ambient interface to a bigger window for more information. 

Weather API: Open Weather Map API 

1) Planning: 
  First sprint (1st Team Deliverable):  
    - Create a paper prototype 
    - Create user questions about project 
    - Present to users the paper prototype and gather data from their input 
    - Make changes to overall paper prototype 
    - Present to users the new prototype and gather information 
    Result: Final Paper Prototype for project 

  Second Sprint (2nd Team Deliverable): 
    - Start developing code for prototype 
    - Choose platform for project (phone, computer, tablet)  
    - Think about design 
    Result: Start project development (ex. use terminal to find data from API) 

  Third Sprint (3rd Team Deliverable): 
    - Record a video describing project progress
    - Keep track of team documents 
    - Keep track of team’s code 
    - Show project progress with code 
    - Reveal what source is keeping track of version control (ex. GitHub) 
    - Start planning for Quality Assurance 
    - Testing 
    - Verification 
    - Validation 
    Result: Working code and video about project progress and documentation, organization, and individual contributions). Develop plans for Quality Insurance. 

  Fourth Sprint (4th Team Deliverable): 
    - Develop code module: 
    - Uses API to update data for a cycle time 
    - Able to show the changes of values (from API) on our module (interface) 
      *** ASK ABOUT SWAP MODULES WITH ANOTHER TEAM 
    Result: Working module that interacts with API and updates in the background 		(retrieves data automatically using time cycle) 

  Fifth Sprint (5th Team Deliverable): 
    - Demonstrate the interface (video) 
    - Show documented results of Quality Assurance (testing, validation, verification) 
    - Create test cases 
        - Array with different numbers for temperature, for loop that goes through every number with specific time interval (5 seconds) 
        - Could use a random number generator every five seconds to change interface 
    - Screen record the window 
    Result: Documentation of project and version control. Want quality assurance tests and record these results. 

Sixth Sprint (6th Team Deliverable): 

Create a video demonstrating the main interface we developed 

Java App 

Develop another interface??? Smaller interface (phone) 

Result: A fully functional java application of our project. Develop another interface??? 

 

Page Break
 

2) Requirements: 

Minimized Window on desktop 

This is the ambience interface 

Must display some current weather information (temperature, description) 

Must update during a time cycle (specific time frame – every ten minutes) 

Must contains button to expand to maximized window 

Maximized Window 

Must contain previous information from last window 

Must have more current information about the weather for the location 

Air quality 

Weather description (ex: cloudy, sunny) 

Must have a search button to change location we are gathering information on 

Changes the bigger window information to searched place 

selected location would change the original window on screen 

Must have a check box (if selected – displays on main screen) 

Must change accordingly to selected location!! 

A search windows to change location 

Must be able to search for a different location 

Must be able to set this new location as “default” 

Extras: 

May be able to change metrics 

May be able to get current location 

There may be a determined number of places users could add or 

There may be unlimited places to add if we can work that out 

Minimized window might change places automatically (set by user) 

 

Specifications: 

APIs 

OpenWeather API will use longitude and latitude information to retrieve specific weather data from the web in JSON file format 

Change temperature 

Air quality 

Other information 

Geocoding API will be used to change user location input to Latitude and longitude to be inputted in the weather API get request  

Allow us to switch locations / places 

Works with weather API to find information based on area 

Java.util Package 

It will be used to get the current time and date 

Timer might be used to control the weather API’s GET request cycles 

Date 

Time / timer 

Calendar 

Java Thread Class 

Yield or sleep might be used to control the weather API’s GET Requeste cycles instead of using the timer 

 

Page Break
 

3) Design and Prototyping: 

Initial Board Prototype: 

 

Minimized Window: 

Fresno, CA 

 

Expand button 

70° 

Sunny 

2:49 PM 

3/26/2022 

  

Location 

Expand button 

degrees temperature 

Weather description 

time 

date 

 

Maximized Window: 

Fresno 

Search Bar 

Minimize button 

Time / Time 

 

 

Temperature 

 

 

Weather 

Picture 

 

Air Quality 

 

 

Wind 

 

 

Visibility 

Select button 

 

Search Bar – allows user to search a location / place 

 

More Locations? 

Search Bar 

Search Button 

Selected location will expand the window and provide the same information as the bigger window 

 

 

Page Break
 

Questions for the User 

 

User Expectation and Impression Questions: 

1. What are your first thoughts after looking at the paper prototype? 

2. What do you think this feature will do? 

3. What is the first thing that you noticed when you looked at the prototype? 

4. When can you imagine yourself using this? 

5. Do you expect to gain anything from this feature? 

a. Does it appear this feature provides that? 

General Task-Driven Feedback Questions: 

What would be the first task you attempt to complete? 

What do you expect to happen when you click the “- “? 

The “+”? 

The “Set” button? 

When you type in the search bar? 

Could you think of an alternative way to expand/close the window (“-“and “+” functions)? 

How about setting a location? 

How about searching for a location? 

Are there any functions that cannot be performed that you would expect? 

What was the hardest task to understand and complete? 

End-of-Session Questions: 

Would you expect the design to be changed at all? If so, how? 

If you could change any of the ways that functions are completed, how would you do it? 

Do you see this feature being easiest to use on mobile devices, computers, or both? 

Do you see a feature like this being useful to yourself? 

If so, do you think it is worth having available at all times? 

 

Questions inspired by: (https://xd.adobe.com/ideas/process/user-research/user-interview-questions-ux-research/) 

Page Break
 

User Feedback: 

Name of user: Genesis, Interviewed by: Vini 

It has the potential to be a nice and informative project at the same time it is lowkey  

Display me information about the weather at my current location 

The location is the first thing that catches my attention on both minimized and maximized screen 

Could be of daily use for anyone working on their computers 

I would use it as long as it does not start automatically when I turn my computer on. Often, I must close various applications from my computer that start alone 

Right now, the interface looks a little crowded which could lead to confusion 

 

Name of user: Dylan, Interviewed by: Vini 

The design needs to be improved in order to be more attractive to a user  

It gives me information about the weather in a city 

The location being displayed  

This product will probably be used on a desktop for whenever someone wants weather information 

I would probably forget about the interface if I had to start it every time I am on my computer. I think it would be interesting for it to be ready to use (look in this case) whenever I need to use it  

I can get the information I need, but it still needs some work on the design 

 

Name of user: Breanna, Interviewed by: Lydia 

It’s nice that the window is not too big 

Shows me the weather, time, date and Air Quality 

Gives me general information 

I can see myself using this if I want to know information on weather 

Expects to able to search other places 

Asks if there would be an “X” button to close out of entire window and return to normal screen 

Improvement: Adding pictures to make it prettier 

Can see this running on a computer screen 

I would use this 

 

Name of user: Sara, Interviewed by: Lydia 

First thoughts: Weather app on the computer 

Has a small box that tells you some information 

When you click on the small box, it expands to a larger box that gives you additional things about the weather 

Confused at first at the “Set” button but then figures it out 

Asks if there would be a drop-down menu for the search bar 

Initially confused about what AQ means 

Can see this used on a computer 

She finds it can be useful 

 

Name of user: Nolan, interviewed by: Michael 

In looking at this prototype, the first thought I have is what platform this template is to be used for, as it looks like the weather widget on PC currently, so I am curious to its differentiation  

I think that this feature will allow users to get more in-depth analysis of weather in an easier way 

I noticed that I liked the font used in the graphics, as it was smooth and pleasing to the eye. Also liked how the city name was bold 

I can imagine myself using this while on the computer 

I can expect to gain easier accessibility to weather updates and I’m not sure if this app has notifications  
 

The plus 

Details on the current city displayed and the ability to search 

    A-details of the city displayed  

    B-to set a new home city or main city for display 

    C-cities in database with weather data  

I like the plus. I think minus could be changed with a three dot 

    A-plus or star 

    B-magnifying glass or map 

 Hourly weather updates 

 how to open the program  
 

 I wouldn't change anything, it is currently ascetically pleasing 

 I would bolden the plus and minus, so they are easier to find  

Both 

 I think it could be good to have when traveling to different places on iPhone as a widget 

 

Page Break
 

Final Paper Prototype: 

 

4) Development 

Flow-chart 

Pseudocode 

Code on white board 

Code design and architecture 

Cohesion and coupling 

Exception handling 

 

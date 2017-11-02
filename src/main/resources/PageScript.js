$().ready(function(){
  fillList();
  $("#GC").click(function(){
    var selectedValue = $("#Program").val();
    if(parseInt(selectedValue) === 0){
      $("#outputText").text("Välj ett program och försök igen.")
    }else{
      $.ajax({
        type: 'get',
        url: "http://localhost:8080/schedule/" + parseInt(selectedValue)
      }).done(function(data){
        handleAuthClick(data)
      })
    }
  });
});


function fillList(){
  $.ajax({
    url: "http://localhost:8080/getAll"
}).then(function(data){
    var j = 1;
    for(i = 0; i < data.length; i++){
      var program = document.createElement("option");
      program.setAttribute("value", j);
      program.appendChild(document.createTextNode(data[i]));
      document.getElementById("Program").appendChild(program);
      j++;
    }

  });
};


var CLIENT_ID = '759261274828-r2ub65t4lujmq7iifo5q5fraa7c9imik.apps.googleusercontent.com';

// Array of API discovery doc URLs for APIs used by the quickstart
var DISCOVERY_DOCS = ["https://www.googleapis.com/discovery/v1/apis/calendar/v3/rest"];

// Authorization scopes required by the API; multiple scopes can be
// included, separated by spaces.
var SCOPES = "https://www.googleapis.com/auth/calendar";

var authorizeButton = document.getElementById('GC');
//   var signoutButton = document.getElementById('signout-button');

/**
 *  On load, called to load the auth2 library and API client library.
 */
function handleClientLoad() {
  gapi.load('client:auth2', initClient);
}

/**
 *  Initializes the API client library and sets up sign-in state
 *  listeners.
 */
function initClient() {
  gapi.client.init({
    discoveryDocs: DISCOVERY_DOCS,
    clientId: CLIENT_ID,
    scope: SCOPES
  }).then(function () {
    // Listen for sign-in state changes.
    gapi.auth2.getAuthInstance().isSignedIn.listen(updateSigninStatus);

    // Handle the initial sign-in state.
   // authorizeButton.onclick = handleAuthClick;
    //signoutButton.onclick = handleSignoutClick;
  });
}

/**
 *  Called when the signed in status changes, to update the UI
 *  appropriately. After a sign-in, the API is called.
 */
function updateSigninStatus(isSignedIn, data) {
  if (isSignedIn) {
    postEvents(data);
  }
}

/**
 *  Sign in the user upon button click.
 */
function handleAuthClick(data) {
  gapi.auth2.getAuthInstance().signIn();
  updateSigninStatus(gapi.auth2.getAuthInstance().isSignedIn.get(), data);
}

/**
 *  Sign out the user upon button click.
 */
//   function handleSignoutClick(event) {
//   gapi.auth2.getAuthInstance().signOut();
//}

/**
 * Append a pre element to the body containing the given message
 * as its text node. Used to display the results of the API call.
 *
 * @param {string} message Text to be placed in pre element.
 */
function appendPre(message) {
  $("#outputText").text(message)

}

/**
 * Print the summary and start datetime/date of the next ten events in
 * the authorized user's calendar. If no events are found an
 * appropriate message is printed.
 */
 function sleep(ms) {
   return new Promise(resolve => setTimeout(resolve, ms));
 }

async function postEvents(data) {
  appendPre("Skickar till Google Calendar...");
  for(var i = 0; i < data.length; i++){
    var eventData = data[i].split("*");
  var event = {
    'summary': eventData[4],
    'location': eventData[3],
    'description': eventData[2],
    'start': {
      'dateTime': eventData[0],
      //'timeZone': 'America/Los_Angeles'
    },
    'end': {
      'dateTime': eventData[1],
      //'timeZone': 'America/Los_Angeles'
    },
    'reminders': {
      'useDefault': false,
      'overrides': [
        {'method': 'email', 'minutes': 24 * 60},
        {'method': 'popup', 'minutes': 10}
      ]
    }
  };

  var request = gapi.client.calendar.events.insert({
    'calendarId': 'primary',
    'resource': event
  });

  request.execute(function(event) {
  });
  await sleep(350);
 }
   appendPre("Färdig!");
}

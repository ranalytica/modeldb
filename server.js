const express = require('express');
const path = require('path');
const app = express();
const api = require('./api');
var dotenv = require('dotenv');
var session = require('express-session');
var auth = require('./routes/auth.js');
var cors = require('cors');
var cookieParser = require('cookie-parser');

dotenv.config();

// config express-session
var sess = {
  secret: 'CHANGE THIS TO A RANDOM SECRET',
  cookie: {},
  resave: false,
  saveUninitialized: true
};

if (app.get('env') === 'production') {
  sess.cookie.secure = true; // serve secure cookies, requires https
}

app.use(cookieParser());
app.use(session(sess));
app.use(cors());

// Load Passport
var passport = require('passport');
var Auth0Strategy = require('passport-auth0');

const secured = (req, res, next) => {
  if (req.isAuthorized() || req.user) {
    return next();
  } else {
    res.send(401, 'user is not authorized');
  }
};

// Configure Passport to use Auth0
var strategy = new Auth0Strategy(
  {
    domain: process.env.AUTH0_DOMAIN,
    clientID: process.env.AUTH0_CLIENT_ID,
    clientSecret: process.env.AUTH0_CLIENT_SECRET,
    callbackURL:
      process.env.AUTH0_CALLBACK_URL
  },
  function (accessToken, refreshToken, extraParams, profile, done) {
    // accessToken is the token to call Auth0 API (not needed in the most cases)
    // extraParams.id_token has the JSON Web Token
    // profile has all the information from the user
    console.log('callback Auth0Strategy', accessToken, refreshToken, extraParams, profile);
    return done(null, profile);
  }
);
passport.use(strategy);

passport.serializeUser(function (user, done) {
  done(null, user);
});

passport.deserializeUser(function (user, done) {
  done(null, user);
});

app.use(passport.initialize());
app.use(passport.session());

app.get('/api/getProjects', (req, res) => {
  api.getFromAPI('/v1/project/getProjects', req.headers)
  .then(response => {
    res.send(response.data);
  })
  .catch(error => {
    res.send(error);
  })
});

app.get('/api/getExperimentRunsInProject', (req, res) => {
  api.getFromAPI(
    '/v1/experiment-run/getExperimentRunsInProject', 
    req.headers,
    req.query) // also req.params for post
  .then(response => {
    res.send(response.data);
  })
  .catch(error => {
    res.send(error);
  })
});

app.use('/api/auth/', auth);

app.get('/api/getUser',
  secured,
  (req, res) => {
    res.json({ user: req.user });
  }
);

// The "catchall" handler: for any request that doesn't
// match one above, send back React's index.html file.
app.get('*', (req, res) => {
  tmpPath = req.path;

  if (tmpPath == '/') {
    tmpPath = '/index.html';
  }
  if (req.user) {
    res.cookie('verta', req.user, {maxAge: 900000, httpOnly: true});
  } else {
  }

  res.header("Cache-Control", "no-cache, no-store, must-revalidate");
  res.header("Pragma", "no-cache");
  res.header("Expires", 0);

  res.sendFile(path.join(__dirname+'/client/build' + tmpPath));
});

app.disable('etags');

const port = process.env.PORT || 3000;
app.listen(port);

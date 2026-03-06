function fn() {
  var env = karate.env || 'local';

  var config = {
    baseUrl: 'http://localhost:8080'
  };

  if (env === 'staging') {
    config.baseUrl = 'https://staging-api.tuempresa.com';
  }

  if (env === 'prod') {
    config.baseUrl = 'https://api.tuempresa.com';
  }

  karate.configure('connectTimeout', 10000);
  karate.configure('readTimeout', 30000);

  // SSL desactivado para ambientes locales/staging con cert autofirmado
  if (env !== 'prod') {
    karate.configure('ssl', true);
  }

  return config;
}
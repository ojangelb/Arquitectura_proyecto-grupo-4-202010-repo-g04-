const config = {
    'key': process.env.KEY || 'supersecret',
    'db': {
        'host': process.env.DB_HOST || 'localhost'
    }
};  

module.exports = config;
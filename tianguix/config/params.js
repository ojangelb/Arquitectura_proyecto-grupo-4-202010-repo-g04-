const config = {
    'key': process.env.KEY || 'supersecret',
    'db': {
        'host': process.env.DB_HOST || 'localhost'
    },
    'asset': {
        'url': process.env.ASSET_HOST || 'localhost',
        'port': process.env.ASSET_PORT || '8090'
    }
};

module.exports = config;
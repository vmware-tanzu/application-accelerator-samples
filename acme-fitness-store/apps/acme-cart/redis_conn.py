#!/usr/bin/python
import os
import redis
from redislite import Redis
from os import environ
from azure_vault import vault_secret


def redis_connection(logger):

    redis_conn_str = vault_secret('CART-REDIS-CONNECTION-STRING')
    if redis_conn_str is None and environ.get('REDIS_CONNECTIONSTRING') not in (None, ''):
        redis_conn_str = str(environ['REDIS_CONNECTIONSTRING'])

    redis_host = environ['REDIS_HOST'] if environ.get('REDIS_HOST') not in (None, '') else None

    redis_port = environ['REDIS_PORT'] if environ.get('REDIS_PORT') not in (None, '') else 6380

    redis_password = os.environ['REDIS_PASSWORD'] if environ.get('REDIS_PASSWORD') not in (None, '') else None

    try:
        if redis_conn_str not in (None, ''):
            logger.info('initiating redis connection using connection string')
            redis_conn = redis.from_url(redis_conn_str)
        elif redis_password is not None:
            logger.info('initiating redis connection with password')
            redis_conn = redis.StrictRedis(host=redis_host, port=redis_port, password=redis_password, db=0, ssl=True)
        elif redis_host not in (None, ''):
            logger.info('initiating redis connection with no password')
            redis_conn = redis.StrictRedis(host=redis_host, port=redis_port, password=None, db=0)
        else:
            logger.info('initiating redis connection with no host or password (using redislite)')
            redis_conn = Redis('redis.db')

        logger.info('initiated redis connection %s', redis_conn)
        redis_conn.ping()
        logger.info('Connected to redis')
        return redis_conn
    except Exception as ex:
        logger.error('Error for redis connection %s', ex)
        exit('Failed to connect, terminating')

from flask import Flask, request, Response
from diningstructs import Establishment, SearchCriteria
from localrandomsearcher import LocalRandomSearcher
import localrandomsearcher
import logging
import os
import json

# Globals
app = Flask(__name__)

_g_searcher = LocalRandomSearcher()

@app.route('/search',methods = ['GET'])
def search():

    if request.method == 'GET':

        diningNames     = request.args.get('diningNames')
        diningTypes     = request.args.get('diningTypes')
        startTime       = request.args.get('startTime')
        endTime         = request.args.get('endTime')

        crit = SearchCriteria("", startTime, endTime, diningTypes, diningNames, "", 0)

        avails = _g_searcher.search(crit)

        return Response(content_type='application/json', response=json.dumps([av.__dict__ for av in avails], default=lambda o: o.__dict__, indent=4), status=200)

    else:

        return Response(status=501)

if __name__ == "__main__":
   logging.basicConfig(format='%(asctime)s,%(msecs)d %(levelname)-8s [%(filename)s:%(lineno)d]    %(message)s', datefmt='%Y-%m-%d:%H:%M:%S',level=logging.DEBUG)
   logging.info('Started')
   app.run(host='0.0.0.0', port=8080, debug=True)
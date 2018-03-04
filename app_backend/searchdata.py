# -*- coding: utf-8 -*-
"""
Created on Sat Mar 3 4:04:31 2018
@author: jakebildy
"""

import os
import sys
from optparse import OptionParser

sys.path.insert(0, '%s/../' % os.path.dirname(__file__))

from common import dump

import ebaysdk
from ebaysdk.finding import Connection as finding
from ebaysdk.exception import ConnectionError


def init_options():
    usage = "usage: %prog [options]"
    parser = OptionParser(usage=usage)

    parser.add_option("-d", "--debug",
                      action="store_true", dest="debug", default=False,
                      help="Enabled debugging [default: %default]")
    parser.add_option("-y", "--yaml",
                      dest="yaml", default='ebay.yaml',
                      help="Specifies the name of the YAML defaults file. [default: %default]")
    parser.add_option("-a", "--appid",
                      dest="appid", default=None,
                      help="Specifies the eBay application id to use.")

    (opts, args) = parser.parse_args()
    return opts, args


def run(opts):

    try:
        api = finding(debug=opts.debug, appid=opts.appid,
                      config_file=opts.yaml, warnings=True)

        api_request = {
            'keywords': u'GRAMMY Foundation®',
            'itemFilter': [
                {'name': 'Condition',
                 'value': 'Used'},
                {'name': 'LocatedIn',
                 'value': 'GB'},
            ],
            'affiliate': {'trackingId': 1},
            'sortOrder': 'CountryDescending',
        }

        response = api.execute('findItemsAdvanced', api_request)

        dump(api)
    except ConnectionError as e:
        print(e)
        print(e.response.dict())



def findSimilarProducts(opts):
    api = finding(siteid='EBAY', debug=opts.debug, appid=opts.appid, config_file=opts.yaml,
                  warnings=True)

    #1. grab JSON from server
    #2. stringify JSON
    #3. api.execute(stringifiedjson)
    #4. return descriptions, etc

    api.execute('findItemsAdvanced', {
    })

    if api.error():
        raise Exception(api.error())

    if api.response_content():
        print("Call Success: %s in length" % len(api.response_content()))

    print("Response code: %s" % api.response_code())
    print("Response DOM: %s" % api.response_dom())

    dictstr = "%s" % api.response_dict()
    print("Response dictionary: %s..." % dictstr[:250])

    print("Finding samples for SDK version %s" % ebaysdk.get_version())
    (opts, args) = init_options()

    findSimilarProducts(opts)

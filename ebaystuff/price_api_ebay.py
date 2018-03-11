import os
import sys
from optparse import OptionParser



from common import dump

import ebaysdk
from ebaysdk.soa.finditem import Connection as FindItem
from ebaysdk.shopping import Connection as Shopping
from ebaysdk.finding import Connection as finding
from ebaysdk.utils import getNodeText
from ebaysdk.exception import ConnectionError


def init_options():
    usage = "usage: %prog --yaml"
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
    parser.add_option("-c", "--consumer_id",
                      dest="consumer_id", default=None,
                      help="Specifies the eBay consumer_id id to use.")

    (opts, args) = parser.parse_args()
    return opts, args


def run(opts):

    try:
        api = finding(debug=opts.debug, appid=opts.appid,
                      config_file=opts.yaml, warnings=True)

        api_request = {
            #'keywords': u'niño',
            'keywords': u'Bicycle',
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


if __name__ == "__main__":
    print("FindItem samples for SDK version %s" % ebaysdk.get_version())
    (opts, args) = init_options()
    run(opts)
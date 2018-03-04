#!/usr/bin/env python2
# -*- coding: utf-8 -*-

"""
Created on Fri Mar  2 23:52:25 2018

@author: vivekmishra
"""

subscription_key = "3c2b41d6a9f6405ca91f53487c48364c"
assert subscription_key
#image_url = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/Broadway_and_Times_Square_by_night.jpg/450px-Broadway_and_Times_Square_by_night.jpg"

import requests
search_url = "https://api.cognitive.microsoft.com/bing/v7.0/images/search"
search_term = "bicycle" 
headers = {"Ocp-Apim-Subscription-Key" : subscription_key}
params  = {"q": search_term, "license": "public", "imageType": "photo"}
response = requests.get(search_url, headers=headers, params=params)
response.raise_for_status()
search_results = response.json()

import json
search_results = json.loads(search_results) 

imageToken = search_results['value'][0]['imageInsightsToken']


search_url = "https://api.cognitive.microsoft.com/bing/v7.0/images/details"
search_term = "bicycle" 
headers = {"Ocp-Apim-Subscription-Key" : subscription_key}
params  = {"q": search_term, "insightsToken": imageToken, "modules": "All"}
response = requests.get(search_url, headers=headers, params=params)
response.raise_for_status()
search_results = response.json()

names = []
for row in search_results['visuallySimilarImages']['value']:
    names.append(row['name'])
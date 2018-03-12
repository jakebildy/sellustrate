from collections import Counter
import random

import networkx as nx
from nltk.corpus import wordnet
from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize
import nltk

fileID = open("descriptions.txt", "r")

descriptions = fileID.read()
posTokens = []

def pos_tokens(sentence):
    tokens = word_tokenize(sentence)  # Generate list of tokens

    iterTokens = tokens.copy()

    for token in iterTokens:
        for key_lemma in wordnet.lemmas(token):
            for related_lemma in key_lemma.derivationally_related_forms():
                tokens.append(related_lemma.name())

    tagged = nltk.pos_tag(tokens)
    return tagged

def clean_up_sentence(sentence):
    sentence = sentence.replace('"', "").replace("\n", "").replace(".", "").replace(",", "").replace("  ", " ").\
        replace("find", "").replace("sell", "").replace("buy", "")

    for i in range(len(stopwords.words('english'))):
        sentence = sentence.replace(" "+stopwords.words('english')[i]+" ", " ")
    return sentence


def get_word_commonality_total(desc_array):
    desc = clean_up_sentence(" ".join(desc_array))
    posTokens.append(pos_tokens(desc))
    words = desc.split(" ")
    counts = Counter(words)
    return counts


def get_word_commonality(sentences, val):
    sentence = sentences[val]
    desc = clean_up_sentence(sentence)
    words = desc.split(" ")
    counts = Counter(words)
    return counts


def generate_descriptions(desc):
    desc_array = desc.lower().split(",")
    response = get_word_commonality_total(desc_array)
    print(get_word_commonality(desc_array, 1))

    print(count_ratio(get_word_commonality(desc_array, 1), get_word_commonality_total(desc_array)))

    return response


def count_ratio(countsSmol, counts):
        return len(counts)/len(countsSmol)


def sounds_like(word1, word2):

    w1 = wordnet.synsets(word1)
    w2 = wordnet.synsets(word2)

    for i in range(len(w1)):
        for j in range(len(w2)):
            try:
                if w1[i].wup_similarity(w2[j]) > 0.8:
                    return True
            except TypeError:
                {}

    return False


def sentence_from_verb(counts):

    gen_desc = ""

    for i in range(5):
        key = counts.most_common(i+1)[i]
        keyWord = key[0]

        try:
            forms = set()

            for key_lemma in wordnet.lemmas(keyWord):

                lemma_list = key_lemma.derivationally_related_forms()

                newList = []
                newList.append(key_lemma)

                for lemma in lemma_list :
                    newList.append(lemma)

                print(newList)

                for related_lemma in newList:
                    word = related_lemma.name()
                    print(word)
                    for i in range(len(posTokens[0])):
                        if word == posTokens[0][i][0]:
                            if posTokens[0][i][1].startswith('NNS') and word not in gen_desc:
                               gen_desc = "Looking for " + word + "? ".join(gen_desc)
                            if posTokens[0][i][1].startswith('NN'):
                                if (word.endswith('ist') or word.endswith('er')) and not word.endswith('wer') and not \
                                        word.endswith('ver') and "to become" not in gen_desc:
                                    gen_desc = "Wanting to become a " + word + "? " + gen_desc
                                elif "may be" not in gen_desc and word not in gen_desc:
                                    gen_desc += "This " + word + " may be just what you're looking for. "

                            if posTokens[0][i][1].startswith('JJ') and word not in gen_desc:
                                if (word.endswith('ist') or word.endswith('er')) and not word.endswith('wer') and not \
                                        word.endswith('ver') and "become" not in gen_desc:
                                    gen_desc = "Looking at becoming a " + word + "? " + gen_desc
                                elif "Passionate" in gen_desc:
                                    gen_desc = "Interested in everything " + word + "? " + gen_desc
                                else:
                                    gen_desc = "Passionate about everything " + word + "? " + gen_desc

        except AttributeError:
            {}
        except IndexError:
            {}
    return gen_desc


qualityIntToDescription = {
        0:
        "The product is brand new and has never been opened.",
        1:
        "The product is brand new, but already opened. Barely touched.",
        2:
        "The product is brand new, but contains some defects. Still more than functional.",
        3:
        "The product is in good condition, having been recently refurbished by the manufacturer.",
        4:
        "The product is in good condition - recently refurbished.",
        5:
        "The product is in absolute mint condition.",
        6:
        "The product is in very good condition.",
        7:
        "The product is used, but still in very good condition.",
        8:
        "The product is quite well used, but still in an acceptable condition. Still works.",
        9:
        "The product is very heavily used, and no longer functions.",
}


randomComment = {
        0:
        " Definitely a great deal.",
        1:
        " Hurry now before it's too late!",
        2:
        " Going fast - make it yours today.",
        3:
        " You don't find a lot of deals like this.",
        4:
        " Definitely worth the price.",
        5:
        " Deals like this are hard to come by.",
    }


print(sentence_from_verb(generate_descriptions(descriptions)))
print(qualityIntToDescription[random.randint(0, 9)]+randomComment[random.randint(0, 5)])
#word1 = input("Enter a word: ")
#word2 = input("Enter a second word: ")
#print(sounds_like(word1, word2))


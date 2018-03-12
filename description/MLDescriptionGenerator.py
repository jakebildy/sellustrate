from collections import Counter
import random
import networkx as nx
from nltk.corpus import wordnet
from nltk.stem.wordnet import WordNetLemmatizer

fileID = open("descriptions.txt", "r")

descriptions = fileID.read()



def clean_up_sentence(sentence):
    return sentence.replace('"', "").replace("\n", "").replace(".", "").replace(",", "").replace("  ", " ")


def get_word_commonality_total(desc_array):
    desc = clean_up_sentence(" ".join(desc_array))
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

    pre_str = "Do you like to "
    post_str = "?"

    for key in counts.most_common(4)[1]:
        try:
            lemmatizer = WordNetLemmatizer()
            word = lemmatizer.lemmatize(key, wordnet.VERB)
            if len(word) > 0:
                return pre_str+word+post_str
        except IndexError:
            {}

    return "NULL"


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


print(generate_descriptions(descriptions))
print(sentence_from_verb(generate_descriptions(descriptions)))
print(qualityIntToDescription[random.randint(0, 9)]+randomComment[random.randint(0, 5)])
word1 = input("Enter a word: ")
word2 = input("Enter a second word: ")
print(sounds_like(word1, word2))


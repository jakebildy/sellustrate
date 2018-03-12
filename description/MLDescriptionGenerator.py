from collections import Counter

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
    return response


print(generate_descriptions(descriptions))

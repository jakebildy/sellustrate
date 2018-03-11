fileID = open("descriptions.txt", "r")

descriptions = fileID.read()


def clean_up_sentence(sentence):
    return sentence.replace('"', "").replace("\n", "").replace(".", "")


def get_word_commonality_total(desc_array):
    desc = clean_up_sentence(" ".join(desc_array))
    return desc


def generate_descriptions(desc):
    desc_array = desc.lower().split()
    response = get_word_commonality_total(desc_array)
    return response


print(generate_descriptions(descriptions))

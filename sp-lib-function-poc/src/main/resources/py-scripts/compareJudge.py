# -*- coding: UTF-8 -*-
import random
import re
import string
import time
from flashtext import KeywordProcessor
 
def compareRegexWithFlash(str_length):
    # generate a random word of given length
    return ''.join(random.choice(string.ascii_lowercase) for _ in range(str_length))
 

# generate a list of 100K words of randomly chosen size
all_words = [compareRegexWithFlash(random.choice([3, 4, 5, 6, 7, 8])) for i in range(100000)]
 
print('Count  | FlashText | Regex     |')
print('−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−')
for keywords_length in [0, 1000, 5000, 10000, 15000]:
    # chose 1000 terms and create a string to search in .
    all_words_chosen = random.sample(all_words, 1000)
    story = ' '.join(all_words_chosen)
 
    # get unique keywords from the l i s t of words generated .
    unique_keywords_sublist = list(set(random.sample(all_words, keywords_length)))
    # compile Regex
    compiled_re = re.compile('|'.join([r'\b' + keyword + r'\b' for keyword in unique_keywords_sublist]))
 
    # add keywords to Flashtext
    keyword_processor = KeywordProcessor()
    # keyword_processor.add_keywords_from_list(unique_keywords_sublist)
    for keyword in unique_keywords_sublist:
        keyword_processor.add_keyword(keyword)
 
    # time the modules
    start = time.time()
    _ = keyword_processor.extract_keywords(story)
    mid = time.time()
    _ = compiled_re.findall(story)
    end = time.time()
 
    # print output
    print(str(keywords_length).ljust(6), '|',
          "{0:.5f}".format(mid - start).ljust(9), '|',
          "{0:.5f}".format(end - mid).ljust(9), '|', )
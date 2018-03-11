% Jake Bildy, 2018
% Generates text from a large body of descriptions using ML

fileID = fopen("descriptions.txt","r");

str = fscanf(fileID,'%c');

generate(str);

function y = generate(str)
descriptions = split(lower(str), ",");

test = getWordCommonalityTotal(descriptions)
y = getWordMatrix(descriptions, 3)

end

function x = getWordMatrix(descriptions, val)

desc = cleanUpSentence(descriptions(val));
words = split(desc, " ");

[~,wordID, count] = unique(words);

n = accumarray(count,1);

x = horzcat(num2cell(n), words(wordID)); %n occurences for each word
end

function x = getWordCommonalityTotal(descriptions)


str = cleanUpSentence(strjoin(descriptions));
words = split(str, " ");

[~,wordID, count] = unique(words);

n = accumarray(count,1);

x = flip(sortrows(horzcat(num2cell(n), words(wordID)),1)); %n occurences for each word
end

function x = cleanUpSentence(str)

%Removes unwanted chars from descriptions
x = regexprep(regexprep(regexprep(regexprep(strrep(strrep(str,':',''),'.',''),'\r',''),'\v',''),'\n',''),'"','');



end


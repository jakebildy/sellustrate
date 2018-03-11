% Jake Bildy, 2018
% Generates text from a large body of descriptions using ML

fileID = fopen("descriptions.txt","r");

str = fscanf(fileID,'%c');

generate(str);

function y = generate(str)
descriptions = split(lower(str), ",");

descOne = descriptions(1);
words = split(descOne, " ");

[~,wordID, count] = unique(words);

n = accumarray(count,1);

y = horzcat(num2cell(n), words(wordID)) %n occurences for each word


end
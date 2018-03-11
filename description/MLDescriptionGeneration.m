% Jake Bildy, 2018
% Generates text from a large body of descriptions using ML

str = "The, quick, quick, quick, brown, fox, jumped, over, the, lazy, dog, dog, thirteen, fourteen, fifteen, sixteen, seventeen, eighteen";
generate(str)

function y = generate(str)
descriptions = split(lower(str), ",");


[~,wordID, count] = unique(descriptions);

n = accumarray(count,1);

y = [n descriptions(wordID)]; %n occurences for each word


end
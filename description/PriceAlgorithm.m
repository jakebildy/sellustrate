% W is the vector of weights for the top n products (most highly ranked)
% p is the vector of prices corresponding to the items of W

n=length(W);
counter=0;
for i=1:n
if W(i)<=0.2
  A(i)=0;
else
  A(i)=W(i);
  counter=counter+1;
end
end

An=zeros(counter,1);
for j=1:counter
An(j)=A(j);
end


for j=1:counter
    PN(j)=p(j);
end
Price=0;
for k=1:counter
    
Price=Price + PN(k)*An(k);
end
Price=Price/counter;

k=5;

if newness==7000
    Price=Price*(0.1);
elseif newness==6000
    Price=Price*(0.25);
elseif newness==5000
    Price=Price*(0.5);
elseif newness==4000
    Price=Price*(0.75);
elseif newness==3000
    Price=Price*(0.9);
elseif newness==2500
    Price=Price*(0.9);
elseif newness==2000
    Price=Price*(0.9);
elseif newness==1750
    Price=Price*(0.9);
elseif newness==1500
    Price=Price*(0.9);
elseif newness==1000
    Price;
end
Price
    
    
    




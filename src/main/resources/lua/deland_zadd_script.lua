if redis.call('zcard', KEYS[1]) > 0 then
       redis.call('del', KEYS[1])
       for i, v in pairs(ARGV) do
           if i > (table.getn(ARGV)) / 2 then
               break
           end
           redis.call('zadd', KEYS[1], ARGV[2*i - 1], ARGV[2*i])
       end
       return 1
else
       for i, v in pairs(ARGV) do
           if i > (table.getn(ARGV)) / 2 then
               break
           end
           redis.call('zadd',KEYS[1], ARGV[2*i - 1], ARGV[2*i])
       end
       return 1
end;
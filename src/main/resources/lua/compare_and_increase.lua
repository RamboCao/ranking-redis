local value = redis.call('get',KEYS[1])
if value then
    if tonumber(value) + ARGV[2] > tonumber(ARGV[1])
        then
            return -1
        else
            return redis.call('incrby',KEYS[1],ARGV[2])
        end
    else
        return redis.call('incrby',KEYS[1],ARGV[2])
end

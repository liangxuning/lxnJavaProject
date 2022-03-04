package com.lxn.learn.netty.rpc;

public class RpcHelloServiceImpl implements IRpcHelloService{
    @Override
    public String hello(String name) {
        return "hello" + name;
    }
}

package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Agent;

public interface AgentServices
{
    Agent save(Agent a);

    Agent findByCode(long agentcode);
}

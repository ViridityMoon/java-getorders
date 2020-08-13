package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Agent;
import com.lambdaschool.crudyorders.repositories.AgentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service(value = "agentServices")
public class AgentServicesImpl implements AgentServices
{
    @Autowired
    AgentsRepository agentrepos;

    @Override
    public Agent save(Agent a) {
        return agentrepos.save(a);
    }

    @Override
    public Agent findByCode(long agentcode)
    {
        return agentrepos.findById(agentcode)
                .orElseThrow(() -> new EntityNotFoundException("Agent " + agentcode + " Not Found!"));
    }
}

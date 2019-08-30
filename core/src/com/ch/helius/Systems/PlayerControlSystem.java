package com.ch.helius.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.ch.helius.Components.BodyComponent;
import com.ch.helius.Components.PlayerComponent;
import com.ch.helius.Components.StateComponent;
import com.ch.helius.SimpleDirectionGestureDetector;

public class PlayerControlSystem extends IteratingSystem {

    ComponentMapper<PlayerComponent> pm;
    ComponentMapper<BodyComponent> bodm;
    ComponentMapper<StateComponent> sm;
    SimpleDirectionGestureDetector controller;


    @SuppressWarnings("unchecked")
    public PlayerControlSystem() {
        super(Family.all(PlayerComponent.class).get());

        pm = ComponentMapper.getFor(PlayerComponent.class);
        bodm = ComponentMapper.getFor(BodyComponent.class);
        sm = ComponentMapper.getFor(StateComponent.class);
    }
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        final BodyComponent b2body = bodm.get(entity);
        final StateComponent state = sm.get(entity);


        SimpleDirectionGestureDetector controller = new SimpleDirectionGestureDetector(new SimpleDirectionGestureDetector.DirectionListener() {

            @Override
            public void onLeft() {
                b2body.body.setLinearVelocity(MathUtils.lerp(b2body.body.getLinearVelocity().x,
                        -5f, 0.2f),b2body.body.getLinearVelocity().y);
            }

            @Override
            public void onRight() {
                b2body.body.setLinearVelocity(MathUtils.lerp(b2body.body.getLinearVelocity().x,
                        5f, 0.2f),b2body.body.getLinearVelocity().y);
            }

            @Override
            public void onUp() {
                if(state.get() == StateComponent.STATE_NORMAL || state.get() == StateComponent.STATE_MOVING){
                    //b2body.body.applyForceToCenter(0, 3000,true);
                    b2body.body.applyLinearImpulse(0, 75f, b2body.body.getWorldCenter().x,
                            b2body.body.getWorldCenter().y, true);
                    state.set(StateComponent.STATE_JUMPING);
                }
            }

            @Override
            public void onDown() {

            }

            @Override
            public void onTap() {

            }
        });
        if(b2body.body.getLinearVelocity().y > 0){
            state.set(StateComponent.STATE_FALLING);
        }

        if(b2body.body.getLinearVelocity().y == 0){
            if(state.get() == StateComponent.STATE_FALLING){
                state.set(StateComponent.STATE_NORMAL);
            }
            if(b2body.body.getLinearVelocity().x != 0){
                state.set(StateComponent.STATE_MOVING);
            }
        }
/*
        if(controller.left){

        }
        if(controller.right){

        }

        if(!controller.left && ! controller.right){
            b2body.body.setLinearVelocity(MathUtils.lerp(b2body.body.getLinearVelocity().x,
                    0, 0.1f),b2body.body.getLinearVelocity().y);
        }

        if(controller.up &&
                (state.get() == StateComponent.STATE_NORMAL || state.get() == StateComponent.STATE_MOVING)){
            //b2body.body.applyForceToCenter(0, 3000,true);
            b2body.body.applyLinearImpulse(0, 75f, b2body.body.getWorldCenter().x,
                    b2body.body.getWorldCenter().y, true);
            state.set(StateComponent.STATE_JUMPING);
        }*/
    }
}

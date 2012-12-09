package com.teknoreality.gameasset.blob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.andengine.*;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.entity.Entity;
import org.andengine.entity.IEntity;
import org.andengine.entity.IEntityComparator;
import org.andengine.entity.IEntityMatcher;
import org.andengine.entity.IEntityParameterCallable;
import org.andengine.entity.modifier.IEntityModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierMatcher;
import org.andengine.entity.primitive.DrawMode;
import org.andengine.entity.primitive.Line;
import org.andengine.entity.primitive.Mesh;
import org.andengine.entity.primitive.vbo.IRectangleVertexBufferObject;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.shape.IAreaShape;
import org.andengine.entity.shape.IShape;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.shader.ShaderProgram;
import org.andengine.opengl.shader.constants.ShaderProgramConstants;
import org.andengine.opengl.util.GLState;
import org.andengine.opengl.vbo.DrawType;
import org.andengine.opengl.vbo.IVertexBufferObject;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.opengl.vbo.attribute.VertexBufferObjectAttributes;
import org.andengine.opengl.vbo.attribute.VertexBufferObjectAttributesBuilder;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.transformation.Transformation;
import org.andengine.util.color.Color;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.shapes.Shape;
import android.opengl.GLES20;
public class Blob extends Mesh  {

	public Blob(float pX, float pY, float[] pBufferData, int pVertexCount,
			DrawMode pDrawMode,
			VertexBufferObjectManager pVertexBufferObjectManager,
			DrawType pDrawType) {
		super(pX, pY, pBufferData, pVertexCount, pDrawMode, pVertexBufferObjectManager,
				pDrawType);
		registerUpdateHandler(new Updatehandler(pBufferData,this));
	}
	
	class Updatehandler implements IUpdateHandler{

		private float[] bufferdata;
		private Mesh mesh;
		private float time=0;
		 List<Float> sinWave= new ArrayList<Float>();
		 List<Float> cosWave= new ArrayList<Float>();
		public Updatehandler(float[] bufferdata,Mesh mesh){
			this.bufferdata= bufferdata;
			 
			for(int i=0;i<=36;i++){
				sinWave.add(1*((float) Math.sin(i*((8*Math.PI)/37))));
				cosWave.add(1*((float) Math.cos(i*((8*Math.PI)/37))));
			}
			this.mesh=mesh; 
		} 
		@Override
		public void onUpdate(float pSecondsElapsed) {
		
			if(time>.027){ 
			for(int i=0;i<=36;i++){
				bufferdata[3*i+3] = bufferdata[3*i+3] +  sinWave.get(i);
				bufferdata[3*i+1+3] =bufferdata[3*i+1+3] + cosWave.get(i);
			}  
			float stemp=sinWave.remove(0);
 			sinWave.add(stemp);
			float ctemp=cosWave.remove(0);
			cosWave.add(ctemp);
			
			//mesh.getVertexBufferObject().onUpdateVertices(mesh);
			
			mesh.setColor(1, 0, 0, 1);
			mesh.getVertexBufferObject().onUpdateColor(mesh);
			time=0;
			} 
			time=time+pSecondsElapsed;
		}

		@Override
		public void reset() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
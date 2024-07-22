package com.libgdx.example;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.libgdx.example.config.GameConfig;

import java.io.ObjectInputFilter;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class GameMain extends ApplicationAdapter {

    public Texture tiles;

    public static TextureRegion[][] splitTiles;

    private SpriteBatch spriteBatch;

    //stage
    public Stage mainStage;
    public Stage leftStage;
    public Stage rightStage;

    //screen view ports
    public FitViewport mainViewport;
    public ScreenViewport leftViewport;
    public ScreenViewport rightViewport;


    //tilemap and tilemap renderer
    public TiledMap tiledMap;
    public OrthogonalTiledMapRenderer tiledMapRenderer;

    //camera
    public OrthographicCamera gameCamera;

    //texture region
    private TextureRegion textureRegion;
    private TextureRegion soldierRegion;
    private TextureRegion healthRegion;
    private TextureRegion ammoRegion;
    private TextureRegion bgLeftPanel;
    private TextureRegion panelBrownItem;
    private TextureRegion panelTomatoGraphics;



    private Skin skin;

    @Override
    public void create() {
        super.create();

        this.init();
    }

    private void init(){
        this.spriteBatch = new SpriteBatch();

        //left viewport
        this.leftViewport = new ScreenViewport();
        //right viewport
        this.rightViewport = new ScreenViewport();
        //main viewport
        this.mainViewport = new FitViewport(GameConfig.WINDOW_WIDTH, GameConfig.WIDOW_HEIGHT);

        //stages
        //left stage
        this.leftStage = new Stage(this.leftViewport);

        //right stage
        this.rightStage = new Stage(this.rightViewport);

        //main stage
        this.mainStage = new Stage(this.mainViewport);

        //tiled map
        this.tiledMap = new TmxMapLoader().load("map/zeldamapfinal.tmx");
        this.tiledMapRenderer = new OrthogonalTiledMapRenderer(
            this.tiledMap,
            GameConfig.UNIT_SCALE,
            this.spriteBatch
        );

        tiles = new Texture(Gdx.files.internal("map/BaseMain.png"));

        //this.tiledMapRenderer.setMap(this.tiledMap);

        //camera
        this.gameCamera = new OrthographicCamera();
        this.gameCamera.setToOrtho(false, GameConfig.WINDOW_WIDTH, GameConfig.WIDOW_HEIGHT);

        this.mainViewport.setCamera(this.gameCamera);



        //additional left and right viewport components
        Texture texture; //= new Texture(Gdx.files.internal("texture.png"));
        //textureRegion = new TextureRegion(texture);

        texture = new Texture(Gdx.files.internal("ui/infantry.png"));
        soldierRegion = new TextureRegion(texture);

        texture = new Texture(Gdx.files.internal("ui/health.png"));
        healthRegion = new TextureRegion(texture);

        texture = new Texture(Gdx.files.internal("ui/ammo.png"));
        ammoRegion = new TextureRegion(texture);

        //texture = new Texture(Gdx.files.internal("ui/testui/files/Button.png"));
        texture = new Texture(Gdx.files.internal("ui/testui/files/panelInset_beige.png"));
        this.bgLeftPanel = new TextureRegion(texture);

        texture = new Texture(Gdx.files.internal("ui/testui/files/panel_brown.png"));
        this.panelBrownItem = new TextureRegion(texture);

        skin = new Skin(Gdx.files.internal("ui/skin/Particle Park UI.json"));


        this.splitTiles = TextureRegion.split(tiles, 16, 16);

        this.panelTomatoGraphics = this.splitTiles[35][0];



        this.tableComponents();





    }

    private void tableComponents(){
        Table root = new Table();
        root.setFillParent(true);

        TextureRegionDrawable drawableLeftPanel = new TextureRegionDrawable(this.bgLeftPanel);
        Image imagePanel1 = new Image(drawableLeftPanel);
        imagePanel1.setScaling(Scaling.fit);
        imagePanel1.setScaleX(10f);
        imagePanel1.setScaleY(20f);
        imagePanel1.setOrigin(20, 50);

        imagePanel1.setZIndex(0);
        root.add(imagePanel1);

        TextureRegionDrawable drawableLeftPanelFirstItem = new TextureRegionDrawable(this.panelBrownItem);
        Image drawableLeftPanelItem1 = new Image(drawableLeftPanelFirstItem);
        drawableLeftPanelItem1.setScaling(Scaling.fit);
        drawableLeftPanelItem1.setScale(0.8f, 0.8f);
        drawableLeftPanelItem1.setOrigin(-900f, 1500f);
        drawableLeftPanelItem1.setZIndex(1);
        root.add(drawableLeftPanelItem1);

        TextureRegionDrawable drawableLeftPanelFirstItemGraphics = new TextureRegionDrawable(this.panelTomatoGraphics);
        Image drawableLeftPanelItem1Graphics = new Image(drawableLeftPanelFirstItemGraphics);
        drawableLeftPanelItem1Graphics.setScaling(Scaling.fit);
        drawableLeftPanelItem1Graphics.setScale(3.5f, 3.5f);
        drawableLeftPanelItem1Graphics.setOrigin(111f, -107f);
        drawableLeftPanelItem1Graphics.setZIndex(2);
        root.add(drawableLeftPanelItem1Graphics);


//        TextureRegionDrawable drawableLeftPanelSecondItem = new TextureRegionDrawable(this.panelBrownItem);
//        Image drawableLeftPanelItem2 = new Image(drawableLeftPanelSecondItem);
//        drawableLeftPanelItem2.setScaling(Scaling.fit);
//        drawableLeftPanelItem2.setScale(0.8f, 0.8f);
//        drawableLeftPanelItem2.setOrigin(-900f, 1500f);
//        drawableLeftPanelItem2.setZIndex(1);
//        root.add(drawableLeftPanelItem2);
//
//        TextureRegionDrawable drawableLeftPanelSecondItemGraphics = new TextureRegionDrawable(this.panelTomatoGraphics);
//        Image drawableLeftPanelItem2Graphics = new Image(drawableLeftPanelSecondItemGraphics);
//        drawableLeftPanelItem2Graphics.setScaling(Scaling.fit);
//        drawableLeftPanelItem2Graphics.setScale(3.5f, 3.5f);
//        drawableLeftPanelItem2Graphics.setOrigin(111f, -107f);
//        drawableLeftPanelItem2Graphics.setZIndex(2);
//        root.add(drawableLeftPanelItem2Graphics);



//        TextureRegionDrawable drawableLeftPanelThirdItem = new TextureRegionDrawable(this.panelBrownItem);
//        Image drawableLeftPanelItem3 = new Image(drawableLeftPanelThirdItem);
//        drawableLeftPanelItem3.setScaling(Scaling.fit);
//        drawableLeftPanelItem3.setScale(0.8f, 0.8f);
//        drawableLeftPanelItem3.setOrigin(-700f, 1500f);
//        drawableLeftPanelItem3.setZIndex(1);
//        root.add(drawableLeftPanelItem3);
//
//        TextureRegionDrawable drawableLeftPanelThirdItemGraphics = new TextureRegionDrawable(this.panelTomatoGraphics);
//        Image drawableLeftPanelItem3Graphics = new Image(drawableLeftPanelThirdItemGraphics);
//        drawableLeftPanelItem3Graphics.setScaling(Scaling.fit);
//        drawableLeftPanelItem3Graphics.setScale(3.5f, 3.5f);
//        drawableLeftPanelItem3Graphics.setOrigin(90f, -107f);
//        drawableLeftPanelItem3Graphics.setZIndex(2);
//        root.add(drawableLeftPanelItem3Graphics);




        leftStage.addActor(root);

        root = new Table();
        root.setFillParent(true);
        root.pad(10);


        root.defaults().space(10);
        TextureRegionDrawable drawable = new TextureRegionDrawable(ammoRegion);
        Image image = new Image(drawable);
        image.setScaling(Scaling.fit);
        root.add(image);

        root.row();
        Label label = new Label("x23", skin);
        root.add(label);

        root.row();
        label = new Label("Primary Objective: Proceed to main living space and erradicate all rodents", skin);
        label.setWrap(true);
        root.add(label).growX();

        rightStage.addActor(root);
    }

    //stage calls go here
    private void updateGamePlay(float delta){
        this.tiledMapRenderer.setView(this.gameCamera);

        this.tiledMapRenderer.render();

        //viewports
        if(this.leftStage != null){
            this.leftStage.act(delta);
            this.leftStage.getViewport().apply();
            this.leftStage.draw();
        }

        if(this.rightStage != null){
            this.rightStage.act(delta);
            this.rightStage.getViewport().apply();
            this.rightStage.draw();
        }


        if(this.mainStage != null){
            this.mainStage.act(delta);
            this.mainStage.getViewport().apply();
            this.mainStage.draw();
        }
    }


    @Override
    public void render() {
        super.render();

        ScreenUtils.clear(Color.DARK_GRAY);

        this.updateGamePlay(Gdx.graphics.getDeltaTime());

        this.spriteBatch.setProjectionMatrix(this.mainViewport.getCamera().combined);
        this.spriteBatch.begin();
        //this.spriteBatch.draw(textureRegion, 0, 0);
        this.spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        this.mainViewport.update(width, height, true);

        this.leftViewport.update(this.mainViewport.getLeftGutterWidth(), height, true);
        this.leftViewport.setScreenPosition(0, 0);

        this.rightViewport.update(this.mainViewport.getRightGutterWidth(), height, true);
        this.rightViewport.setScreenPosition(this.mainViewport.getRightGutterX(), 0);



    }

    @Override
    public void dispose() {
        super.dispose();

        this.spriteBatch.dispose();
    }
}

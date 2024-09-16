package com.br1ghtsteel.palehunger.entity.client;

import com.br1ghtsteel.palehunger.entity.LurcherEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class LurcherModel<T extends LurcherEntity> extends EntityModel<T> {
	private final ModelPart Body;
	private final ModelPart Head;
	private final ModelPart BackLegs;
	private final ModelPart BackRight;
	private final ModelPart BackLeft;
	private final ModelPart FrontLegs;
	private final ModelPart FrontLeft;
	private final ModelPart FrontRight;

	public LurcherModel(ModelPart root) {
		this.Body = root.getChild("Body");
		this.Head = this.Body.getChild("Head");
		this.BackLegs = root.getChild("BackLegs");
		this.BackRight = this.BackLegs.getChild("BackRight");
		this.BackLeft = this.BackLegs.getChild("BackLeft");
		this.FrontLegs = root.getChild("FrontLegs");
		this.FrontLeft = this.FrontLegs.getChild("FrontLeft");
		this.FrontRight = this.FrontLegs.getChild("FrontRight");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData Body = modelPartData.addChild("Body", ModelPartBuilder.create().uv(27, 24).cuboid(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 5.0F, new Dilation(0.0F))
		.uv(0, 20).cuboid(0.0F, -16.0F, 2.0F, 0.0F, 15.0F, 9.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 18.0F, 0.0F));

		ModelPartData GiantMushroom_r1 = Body.addChild("GiantMushroom_r1", ModelPartBuilder.create().uv(32, 0).cuboid(-10.0F, -13.0F, 1.0F, 13.0F, 13.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, -3.0F, 1.0F, 0.0F, 3.1416F, 0.0F));

		ModelPartData Head = Body.addChild("Head", ModelPartBuilder.create().uv(48, 13).cuboid(-8.8F, -4.6F, 2.0F, 5.0F, 6.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-4.0F, -7.0F, -2.0F, 8.0F, 7.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -12.0F, -4.0F));

		ModelPartData Mold_r1 = Head.addChild("Mold_r1", ModelPartBuilder.create().uv(0, 15).cuboid(-3.0F, -9.0F, -3.0F, 8.0F, 6.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(9.4F, -2.5F, 0.0F, 0.0F, 0.0F, -1.5708F));

		ModelPartData Mushroom_r1 = Head.addChild("Mushroom_r1", ModelPartBuilder.create().uv(0, 44).cuboid(4.0F, -10.0F, 0.0F, 5.0F, 6.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(12.9F, 1.0F, 2.0F, 0.0F, 3.1416F, 0.0F));

		ModelPartData BackLegs = modelPartData.addChild("BackLegs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData BackRight = BackLegs.addChild("BackRight", ModelPartBuilder.create().uv(14, 40).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, -7.0F, 4.0F));

		ModelPartData BackLeft = BackLegs.addChild("BackLeft", ModelPartBuilder.create().uv(46, 41).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, -6.0F, 4.0F));

		ModelPartData FrontLegs = modelPartData.addChild("FrontLegs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData FrontLeft = FrontLegs.addChild("FrontLeft", ModelPartBuilder.create(), ModelTransform.pivot(2.0F, -7.0F, -4.0F));

		ModelPartData FrontLeftLeg_r1 = FrontLeft.addChild("FrontLeftLeg_r1", ModelPartBuilder.create().uv(32, 13).cuboid(-2.0F, 0.0F, 0.0F, 4.0F, 7.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 2.0F, 0.0F, 3.1416F, 0.0F));

		ModelPartData FrontRight = FrontLegs.addChild("FrontRight", ModelPartBuilder.create(), ModelTransform.pivot(-2.0F, -6.0F, -4.0F));

		ModelPartData FrontRightLeg_r1 = FrontRight.addChild("FrontRightLeg_r1", ModelPartBuilder.create().uv(30, 41).cuboid(-2.0F, 0.0F, 0.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 2.0F, 0.0F, 3.1416F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		Body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		BackLegs.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		FrontLegs.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

    }
}
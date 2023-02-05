#version 150

uniform sampler2D DiffuseSampler;

in vec2 texCoord;
in vec2 oneTexel;

uniform vec2 InSize;

uniform vec3 ConvergeX;
uniform vec3 ConvergeY;
uniform vec3 RadialConvergeX;
uniform vec3 RadialConvergeY;
uniform float Strength;

out vec4 fragColor;

void main() {
    vec3 mixedRadialConvergeX = mix(vec3(1.0, 1.0, 1.0), RadialConvergeX, Strength);
    vec3 mixedRadialConvergeY = mix(vec3(1.0, 1.0, 1.0), RadialConvergeY, Strength);
    vec3 CoordX = texCoord.x * mixedRadialConvergeX;
    vec3 CoordY = texCoord.y * mixedRadialConvergeY;

    CoordX += (ConvergeX * oneTexel.x - (mixedRadialConvergeX - 1.0) * 0.5) * Strength;
    CoordY += (ConvergeY * oneTexel.y - (mixedRadialConvergeY - 1.0) * 0.5) * Strength;

    vec4 value = texture(DiffuseSampler, texCoord);
    float GreenValue = texture(DiffuseSampler, vec2(CoordX.z, CoordY.z)).g;
    float BlueValue = texture(DiffuseSampler, vec2(CoordX.z, CoordY.z)).b;
    float AlphaValue = texture(DiffuseSampler, texCoord).a;

    fragColor = vec4(value.r, GreenValue, BlueValue, 1.0);
}

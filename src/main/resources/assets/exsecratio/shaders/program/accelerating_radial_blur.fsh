#version 150

uniform sampler2D DiffuseSampler;

in vec2 texCoord;
in vec2 oneTexel;

uniform vec2 InSize;
uniform vec2 OutSize;
uniform float Strength;

out vec4 fragColor;

const int nsamples = 10;

void main() {
    vec2 center = vec2(0.5, 0.5);
    float blurStart = 1;
    float blurWidth = 0.05 * Strength;


    vec2 uv = texCoord.xy - center;
    float precompute = blurWidth * (1.0 / float(nsamples - 1));

    vec4 color = vec4(0.0);
    for (int i = 0; i < nsamples; i++)
    {
        float scale = blurStart + (float(i)* precompute);
        color += texture(DiffuseSampler, uv * scale + center) * vec4(1 - uv.y * Strength, 1.0, 1.0, 1.0);
    }
    color /= float(nsamples);

    fragColor = color;
}